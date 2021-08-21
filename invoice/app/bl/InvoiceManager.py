import logging
from json import load as json_load
from entities.InvoiceEntity import InvoiceEntity
from repository.InvoiceRepository import InvoiceRepository
from streaming.KafkaWriter import OpsKafkaWriter

import utils.Utils as Utils
import constants.constants as constants
import constants.envargs as envargs


class InvoiceManager(object):

    def __init__(self, topic, messages):
        self.logger = logging.getLogger(self.__class__.__name__)
        self.topic = topic
        self.messages = messages
        self.success = False

    def execute(self):
    
        invoices = self.consolidate_invoices()    
        saved_invoices = self.save_invoices(invoices)
        self.send_to_invoice_topic(saved_invoices)
        self.success = True

    def view_messages(self, invoices):
        for invoice in invoices:
            self.logger.info(invoice.generate_req())
    
    def consolidate_invoices(self):
        invoice_entities = []        

        for message in self.messages:        
            msg_value = message.value
            invoice_entity = InvoiceEntity(msg_value)
            invoice_entities.append(invoice_entity)
            
        return invoice_entities

    def save_invoices(self, invoices):
        saved_invoices = []
        invoice_repository = InvoiceRepository()
        for invoice in invoices:            
            saved_invoice = invoice_repository.save_invoice(invoice)
            saved_invoices.append(saved_invoice)
        return saved_invoices

    def send_to_invoice_topic(self, invoices):
        kafka_writer = OpsKafkaWriter()
        for invoice in invoices:
            kafka_writer.send_message(invoice.generate_req_to_send())
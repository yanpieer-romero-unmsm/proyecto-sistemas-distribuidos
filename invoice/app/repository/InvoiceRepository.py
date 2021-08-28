from repository.dao import daoInvoice
from json import dumps


class InvoiceRepository(object):
    def save_invoice(self, invoice_entity):
        data = invoice_entity.generate_req_to_send()
        invoice_id = data['invoiceId']
        total_igv = data['igvTotal']
        total_invoice = data['invoiceTotal']
        client = dumps(data['client'])
        articles = dumps(data['articles'])
        daoInvoice.save_invoice(invoice_id, total_igv, total_invoice, client, articles)

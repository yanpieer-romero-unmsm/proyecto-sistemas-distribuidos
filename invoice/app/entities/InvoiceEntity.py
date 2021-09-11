import utils.Utils as Utils
from json import load as json_load
import uuid


class InvoiceEntity(object):

    KEY_CODE = 'code'
    KEY_ARTICLES = 'articles'
    KEY_CLIENT = 'client'
    KEY_NAME = 'name'
    KEY_UNIT_PRICE = 'unitPrice'
    KEY_QUANTITY = 'quantity'
    KEY_CLIENT_RUC = 'ruc'
    KEY_INVOICE_ID = 'invoiceId'
    KEY_CLIENT_ID = 'clientId'
    KEY_TOTAL_IGV = 'igvTotal'
    KEY_TOTAL_INVOICE = 'invoiceTotal'
    KEY_SUBTOTAL = 'subtotal'
    KEY_ORDER_ID = 'orderId'

    def __init__(self, invoice):
        self.invoice = invoice

    def generate_req_to_send(self):
        request = {
            self.KEY_ORDER_ID: self.get_order_id(),
            self.KEY_INVOICE_ID: str(uuid.uuid4()),
            self.KEY_TOTAL_IGV: self.get_igv(),
            self.KEY_TOTAL_INVOICE: self.get_invoice(),
            self.KEY_CLIENT: self.get_client(),
            self.KEY_ARTICLES: self.articles_to_send()
        }
        return request

    def get_article(self):
        return self.invoice[self.KEY_ARTICLES]

    def get_client(self):
        return self.invoice[self.KEY_CLIENT]

    def get_invoice(self):
        total_invoice = 0
        articles = self.get_article()
        for article in articles:
            quantity = article[self.KEY_QUANTITY]
            unit_price = article[self.KEY_UNIT_PRICE]
            total_invoice = total_invoice + quantity*unit_price
        return total_invoice

    def get_igv(self):
        total_invoice = self.get_invoice()
        igv = Utils.get_total_igv(total_invoice)
        return igv

    def articles_to_send(self):
        articles = self.get_article()
        for article in articles:
            quantity = article[self.KEY_QUANTITY]
            unit_price = article[self.KEY_UNIT_PRICE]
            article[self.KEY_SUBTOTAL] = quantity*unit_price
        return articles

    def get_order_id(self):
        return self.invoice[self.KEY_ORDER_ID]

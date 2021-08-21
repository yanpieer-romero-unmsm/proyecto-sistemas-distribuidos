import utils.Utils as Utils

class InvoiceEntity(object):
    
    KEY_CODE = 'code'
    KEY_ARTICLE = 'article'
    KEY_CLIENT = 'client'
    KEY_NAME = 'name'
    KEY_UNIT_PRICE = 'unitPrice'
    KEY_QUANTITY = 'quantity'
    KEY_CLIENT_RUC = 'ruc'

    KEY_CLIENT_ID = 'clientId'
    KEY_TOTAL_IGV = 'igvTotal'
    KEY_TOTAL_INVOICE = 'invoiceTotal'

    def __init__(self, invoice):        
        self.invoice = invoice

    def generate_req_to_send(self):
        request = {
            self.KEY_CLIENT_ID: self.get_client_ruc(),
            self.KEY_TOTAL_IGV: self.get_igv(),
            self.KEY_TOTAL_INVOICE: self.get_invoice(),
        }
        return request

    def get_article(self):
        return self.invoice[self.KEY_ARTICLE]

    def get_client(self):
        return self.invoice[self.KEY_CLIENT]

    def get_article_code(self):
        article = self.get_article()
        return article[self.KEY_CODE]

    def get_article_name(self):
        article = self.get_article()
        return article[self.KEY_NAME]

    def get_article_unit_price(self):
        article = self.get_article()
        return article[self.KEY_UNIT_PRICE]

    def get_article_quantity(self):
        article = self.get_article()
        return article[self.KEY_QUANTITY]

    def get_client_ruc(self):
        client = self.get_client()
        return client[self.KEY_CLIENT_RUC]

    def get_client_name(self):
        client = self.get_client()
        return client[self.KEY_NAME]

    def get_invoice(self):
        quantity = self.get_article_quantity()
        unit_price = self.get_article_unit_price()
        total_invoice = Utils.get_total_invoice(unit_price, quantity)
        return total_invoice

    def get_igv(self):
        total_invoice = self.get_invoice()
        igv = Utils.get_total_igv(total_invoice)
        return igv
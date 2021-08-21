import logging


class ProductRepository(object):

    def __init__(self, base_url):
        self.logger = logging.getLogger(self.__class__.__name__)
        self.base_url = base_url
        super().__init__(self.logger)

    def save_many(self, products):
        url = f'{self.base_url}/product'
        created = super().save_many(products, url)
        return created

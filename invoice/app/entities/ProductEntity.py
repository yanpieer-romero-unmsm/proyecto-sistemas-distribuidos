from entities.interface.IEntity import IEntity
import constants.parameters as parameters


class ProductEntity(IEntity):

    KEY_CODE = 'code'
    KEY_BRAND = 'brand'
    KEY_BRAND_NAME = 'name'
    KEY_TITLE = 'title'
    KEY_COMPOSITION = 'composition'
    KEY_DELETE = 'delete'
    KEY_IMPORT_DATA = 'import_data'
    KEY_SHOP_ID = 'shop_id'
    KEY_TYPE_ACTION = 'type_action'
    KEY_PRODUCT = 'product'
    KEY_PARENT_CODE = 'parent_code'

    def __init__(self, inc_payload, delete, type_action):
        super().__init__(delete)        
        self.inc_payload = inc_payload
        self.type_action = type_action

    def entity_code(self):
        return self.get_code()

    def generate_payload(self):
        delete = super().get_delete()
        payload = {
            self.KEY_CODE: self.get_code(),
            self.KEY_COMPOSITION: self.get_composition(),
            self.KEY_DELETE: delete,
            self.KEY_TYPE_ACTION: self.type_action
        }

        if self.get_composition() in [parameters.OPS_PRODUCT_COMPOSITION_BASE, parameters.OPS_PRODUCT_COMPOSITION_SIMPLE]:
            payload[self.KEY_TITLE] = self.get_title()
            payload[self.KEY_BRAND] = self.get_brand()
        elif self.get_composition() == parameters.OPS_PRODUCT_COMPOSITION_VARIANT:
            payload[self.KEY_PARENT_CODE] = self.get_parent_code()

        return payload

    def get_product(self):
        return self.inc_payload[self.KEY_PRODUCT]

    def get_code(self):
        product = self.get_product()
        return product[self.KEY_CODE]

    def get_brand(self):
        product = self.get_product()
        brand = product[self.KEY_BRAND]
        return brand[self.KEY_BRAND_NAME]

    def get_title(self):
        product = self.get_product()
        return product[self.KEY_TITLE]

    def get_composition(self):
        product = self.get_product()
        return product[self.KEY_COMPOSITION]

    def get_import_data(self):        
        return self.inc_payload[self.KEY_IMPORT_DATA]

    def get_shop_id(self):
        import_data = self.get_import_data()
        return import_data[self.KEY_SHOP_ID]    

    def get_parent_code(self):
        product = self.get_product()
        return product[self.KEY_PARENT_CODE]

    def skip_entity(self):
        # Validate if product event will be discard
        shop_id = self.get_shop_id()   
        if shop_id == parameters.OPS_PRODUCT_IGNORED_SHOP_ID:
            return True
        return False

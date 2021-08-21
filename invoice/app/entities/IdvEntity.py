from entities.interface.IEntity import IEntity
import constants.parameters as parameters


class IdvEntity(object):

    KEY_DRAFT_IDV = 'draft_idv'
    KEY_CODE = 'code'
    KEY_PRODUCT_CODE = 'product_code'
    KEY_SKU_MKP = 'sku_mkp'
    KEY_SKU_SELLER = 'sku_seller'
    KEY_TYPE = 'type'
    KEY_IS_PUBLISHED = 'is_published'
    KEY_IS_VALID = 'is_valid'
    KEY_IS_ENABLED = 'is_enabled'
    KEY_TYPE_ACTION = 'type_action'
    KEY_PRICES = 'prices'
    KEY_DELETE = 'delete'
    KEY_SHOP_ID = 'shop_id'
    KEY_COMBO_RELATION = 'combo_relation'
    KEY_RELATED_SERVICES = 'related_services'
    KEY_PRODUCT_DATA = 'product_data'

    def __init__(self, inc_payload, delete, type_action):
        super().__init__(delete)
        self.inc_payload = inc_payload
        self.type_action = type_action

    def generate_request(self):
        request = super().generate_request()
        request[self.KEY_PRODUCT_CODE] = self.get_product_code()
        return request

    def entity_code(self):
        return self.get_sku_mkp()

    def generate_payload(self):
        delete = super().get_delete()        
        payload = {
            self.KEY_CODE: self.get_code(),
            self.KEY_PRODUCT_CODE: self.get_product_code(),
            self.KEY_SKU_SELLER: self.get_sku_seller(),
            self.KEY_SKU_MKP: self.get_sku_mkp(),
            self.KEY_TYPE: self.get_type(),
            self.KEY_TYPE_ACTION: self.type_action,
            self.KEY_IS_PUBLISHED: self.to_be_published(),
            self.KEY_IS_VALID: self.get_is_valid(),
            self.KEY_IS_ENABLED: self.get_is_enabled(),
            self.KEY_PRICES: self.get_prices(),
            self.KEY_SHOP_ID: self.get_shop_id(),
            self.KEY_PRODUCT_DATA: self.get_product_data(),
            self.KEY_DELETE: delete
        }

        if self.get_type() == parameters.OPS_IDV_TYPE_COMBO:
            payload[self.KEY_COMBO_RELATION] = self.get_combo_relation()

        return payload

    def get_draft_idv(self):
        return self.inc_payload[self.KEY_DRAFT_IDV]

    def get_code(self):
        return self.get_sku_mkp()

    def get_product_code(self):
        draft_div = self.get_draft_idv()
        return draft_div[self.KEY_PRODUCT_CODE]

    def get_sku_seller(self):
        draft_div = self.get_draft_idv()
        return draft_div[self.KEY_SKU_SELLER]

    def get_sku_mkp(self):
        draft_div = self.get_draft_idv()
        return draft_div[self.KEY_SKU_MKP]

    def get_type(self):
        draft_div = self.get_draft_idv()
        return draft_div[self.KEY_TYPE]

    def get_is_published(self):
        draft_div = self.get_draft_idv()
        return draft_div[self.KEY_IS_PUBLISHED]

    def to_be_published(self):
        if self.type_action == 'IDV_CREATED':
            if self.get_is_valid():
                return True
            else:
                return False
        elif self.type_action == 'IDV_UPDATED':
            if not self.get_is_valid() or not self.get_is_enabled() or not self.get_is_published():
                return False
            else:
                return True

    def get_is_enabled(self):
        draft_div = self.get_draft_idv()
        return draft_div[self.KEY_IS_ENABLED]

    def get_is_valid(self):
        draft_div = self.get_draft_idv()
        return draft_div[self.KEY_IS_VALID]

    def get_prices(self):
        draft_div = self.get_draft_idv()
        return draft_div[self.KEY_PRICES]

    def get_shop_id(self):
        draft_div = self.get_draft_idv()
        return draft_div[self.KEY_SHOP_ID]

    def get_combo_relation(self):
        draft_div = self.get_draft_idv()
        return draft_div[self.KEY_COMBO_RELATION]

    def get_related_services(self):
        draft_div = self.get_draft_idv()
        return draft_div[self.KEY_RELATED_SERVICES]

    def get_product_data(self):
        if self.get_shop_id() == parameters.OPS_PRODUCT_RIPLEY_SHOP_ID:
            draft_div = self.get_draft_idv()
            return draft_div[self.KEY_PRODUCT_DATA]
        else:
            return None

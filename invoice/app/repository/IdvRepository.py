import logging
import utils.Utils as Utils
import utils.RestUtils as RestUtils
import constants.constants as constants


class IdvRepository(object):

    def __init__(self, base_url):
        self.logger = logging.getLogger(self.__class__.__name__)
        self.base_url = base_url
        super().__init__(self.logger)

    def save_many(self, products):
        url = f'{self.base_url}/idv'
        created = super().save_many(products, url)
        return created

    def get_many(self, codes):
        idvs = {}
        limit = 10
        page = 1
        max_rows = 10        

        keys_to_send = Utils.split_array(codes, max_rows)
        for chunk in keys_to_send:
            codes = ','.join(chunk)
            url = f'{self.base_url}/idv?limit={limit}&page={page}&codes={codes}'

            response = RestUtils.get(url)
            if response[constants.RES_STATUS_CODE] == 200:
                data = response[constants.RES_DATA]
                res_idvs = data[constants.RES_KEY_ITEMS]

                for idv in res_idvs:                    
                    idv_payload = idv['payload']             
                    code = idv['code']
                    idvs[code] = idv_payload
            else:
                raise Exception('Error al obtener los productos.')
        return idvs

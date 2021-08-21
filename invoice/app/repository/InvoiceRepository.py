import logging
import json
import utils.Utils as Utils
import utils.RestUtils as RestUtils
import constants.constants as constants


class InvoiceRepository(object):

    def __init__(self):
        self.logger = logging.getLogger(self.__class__.__name__)            

    def save_many(self, invoices):
        url = f'{constants.BASE_URL}/invoices'
        created = super().save_many(invoices, url)
        return created

    def save_invoice(self, invoice):        
        url = f'{constants.BASE_URL}/invoices'  
        request = invoice.generate_req_to_send()  
        response_status = RestUtils.post(url, headers={}, **{constants.REQ_JSON: request})
        if response_status == 201:
            self.logger.info(f'Se registro la factura para el cliente {invoice.get_client_name()} con RUC {invoice.get_client_ruc()}')        
            return invoice 
        else:
            raise Exception('Error al guardar las facturas.')        

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

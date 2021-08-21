import logging
from json import load as json_load

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

        self.logger.info("todo bien todo correcto")
        self.view_messages()
    #    self.verify()
        

        self.success = True

    def view_messages(self):
        for message in self.messages:
            self.logger.info(message)
    
"""
    def create_entities(self):
        entity_name = self.get_consumer_entity_name()
        delete = self.get_consumer_delete()

        entity_factory = EntityFactory()

        entities = []
        for message in self.messages:        
            msg_value = message.value
            type_action = msg_value['event_name']
            payload_list = msg_value['payload']

            for inc_payload in payload_list:
                entity = entity_factory.get_entity(entity_name, inc_payload, delete, type_action)            
                if entity.validate_entity():
                    if not entity.skip_entity():
                        entities.append(entity)
                    else:
                        warning_msg = (f"Exclude entity {entity_name} "
                                       f"with code: { entity.entity_code()}  and shop_id: {entity.get_shop_id()} ")                   
                        self.logger.warning(warning_msg)            
        return entities

    def add_old_payload(self, entities):
        idv_codes = []
        for entity in entities:
            idv_codes.append(entity.get_code())

        idvRepository = IdvRepository(envargs.STORAGE_API_URL)
        idvs_from_pg = idvRepository.get_many(idv_codes)

        for entity in entities:
            code = entity.get_code()
            if code in idvs_from_pg:
                old_payload = idvs_from_pg[code]
                entity.inc_payload['old_payload'] = old_payload
            else:
                entity.inc_payload['old_payload'] = ''            
        return entities

    def write_entities(self, entities):
        if len(entities) == 0:
            return

        entity_name = self.get_consumer_entity_name()

        req_entities = []
        for entity in entities:
            payload = entity.generate_request()
            req_entities.append(payload)

        repository_factory = RepositoryFactory()
        repository = repository_factory.get_repository(entity_name, envargs.STORAGE_API_URL)
        repository.save_many(req_entities)

    def enqueue_entities(self, entities):
        entity_name = self.get_consumer_entity_name()

        kafka_writer = OpsKafkaWriter()        
        for entity in entities:            
            entity_key = entity.entity_code()
            entity_payload = entity.generate_request()
            entity_payload['old_payload'] = entity.inc_payload['old_payload']

            message = {
                'entity': entity_name,
                'key': entity_key,
                'payload': entity_payload
            }
            kafka_writer.send_message(message)

        self.logger.info('Entity enqueue successfully')

    def get_consumer_entity_name(self):
        return self.consumer[constants.CONSUMERSJSON_KEY_ENTITY]

    def get_consumer_delete(self):
        self.logger.info(self.consumer)
        return self.consumer[constants.CONSUMERSJSON_KEY_ENTITY]

    def is_consumer_send_to_commerce(self):        
        return self.consumer[constants.CONSUMERSJSON_KEY_SEND_TO_COMMERCE]
"""
    
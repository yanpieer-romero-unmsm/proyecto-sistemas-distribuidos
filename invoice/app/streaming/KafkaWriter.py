import logging

import constants.envargs as envargs
from json import dumps
from kafka import KafkaProducer


class OpsKafkaWriter(object):

    def __init__(self):
        self.logger = logging.getLogger(self.__class__.__name__)
        self.producer = None

    def get_kafka_producer(self):
        """
        if kafka producer is not connected, then start the connection
        """
        if self.producer is None:       
            self.logger.info(f'Conectado al Kafka: {envargs.KAFKA_SERVER}:{envargs.KAFKA_PORT} - Topic: {self.topic}')
            self.producer = KafkaProducer(                
                bootstrap_servers=[f'{envargs.KAFKA_SERVER}:{envargs.KAFKA_PORT}'],
                value_serializer=lambda x: dumps(x).encode('utf-8')
            )

        return self.producer

    def send_message(self, message):
        self.get_kafka_producer().send(envargs.KAFKA_TOPIC_OUT, value=message)
        self.logger.info(f'Mensaje enviado al tópico: {envargs.KAFKA_TOPIC_OUT}')
        self.logger.info(f'Mensaje enviado: {message}')

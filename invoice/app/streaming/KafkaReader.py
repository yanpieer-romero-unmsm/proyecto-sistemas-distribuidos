import logging
from json import loads

import utils.LogHandler  # noqa
import constants.envargs as envargs
from time import sleep
from kafka import KafkaConsumer
from bl.InvoiceManager import InvoiceManager


class OpsKafkaReader(object):

    def __init__(self, topic):
        self.logger = logging.getLogger(self.__class__.__name__)
        self.topic = topic
        self.consumer = None

    def get_kafka_consumer(self):
        """
        if kafka consumer is not connected, then start the connection
        """
        if self.consumer is None:
            self.logger.info(f'Conectado al Kafka: {envargs.KAFKA_SERVER}:{envargs.KAFKA_PORT} - Topic: {self.topic}')
            self.consumer = KafkaConsumer(                
                envargs.KAFKA_TOPIC,
                bootstrap_servers=[f'{envargs.KAFKA_SERVER}:{envargs.KAFKA_PORT}'],
                auto_offset_reset='earliest',
                enable_auto_commit=False,
                group_id='unmsm-group',
                value_deserializer=lambda x: loads(x.decode('utf-8')),
                api_version = (0,10,1)
            )

        return self.consumer

    def poll(self):
        return self.get_kafka_consumer().poll(timeout_ms=envargs.KAFKA_POLL_TIMEOUT, max_records=envargs.KAFKA_POLL_MAXRECORDS)

    def acknowledge(self):
        self.get_kafka_consumer().commit()

    def consume(self):
        raw_messages = self.poll()
        items = raw_messages.items()
        for topic_partition, messages in items:
            self.logger.info(f' topic_partition> {topic_partition}')
            self.logger.debug(f' messages> {messages}')
            entity_manager = InvoiceManager(self.topic, messages)
            entity_manager.execute()
            if entity_manager.success:
                self.acknowledge()
                self.logger.info('acknowledge.!')

    def _setattr(self, name, value):
        object.__setattr__(self, name, value)


def consume(topic):
    """
    creates a Kafka consumer and starts listening
    """
    kafka_reader = OpsKafkaReader(topic)
    while True:
        kafka_reader.consume()
        sleep(1)

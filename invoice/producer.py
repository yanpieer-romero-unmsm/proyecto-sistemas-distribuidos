from kafka import KafkaProducer
from json import dumps
from time import sleep
import logging
logging.getLogger().setLevel(logging.INFO)
logging.basicConfig(format='%(asctime)s - %(levelname)s: %(message)s', datefmt='%d-%m-%Y %H:%M:%S')

producer = KafkaProducer(bootstrap_servers='localhost:9092', value_serializer=lambda x: dumps(x).encode('utf-8'), api_version = (0,10,1))
# producer = KafkaProducer(bootstrap_servers=['35.224.44.47:9092'], value_serializer=lambda x: dumps(x).encode('utf-8'))
# producer = KafkaProducer(bootstrap_servers=['localhost:9093'], value_serializer=lambda x: dumps(x).encode('utf-8'))
"""producer = KafkaProducer(
    bootstrap_servers=['pkc-419q3.us-east4.gcp.confluent.cloud:9092'],
    security_protocol="SASL_SSL",
    sasl_mechanism='PLAIN',
    sasl_plain_username='WK6KXT2FYXKIBSTL',
    sasl_plain_password='LOfeIr2+xc2SWjun+fweZq8Xvjt3VpLnXPI/UBvXsioTCx67KSjW+iNtuetSHp3Q',
    value_serializer=lambda x: dumps(x).encode('utf-8')
)"""

def create_reservation_message(correlative):
    data = {
		"article": {
			"code": correlative,
			"name": "name0001",
			"unitPrice": 15.0,
			"quantity": 3
		},
		"client": {
			"ruc": "17200140",
			"name": "Edinson Boada"
		}
	}
    return data


for e in range(10):    

    # message = {"payload": create_product_payload(correlative)}
    # producer.send('testuno', value=message)
    # logging.info(message)
    # sleep(0.1)
    correlative = str(27200140+e)
    message = create_reservation_message(correlative)
   # {"payload": [create_idv_payload(correlative)]}
    producer.send('reservation_topic', value=message)
    logging.info(message)
    sleep(0.1)
sleep(1)

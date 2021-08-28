from kafka import KafkaProducer
from json import dumps
from time import sleep
import logging
logging.getLogger().setLevel(logging.INFO)
logging.basicConfig(format='%(asctime)s - %(levelname)s: %(message)s', datefmt='%d-%m-%Y %H:%M:%S')

producer = KafkaProducer(bootstrap_servers='localhost:9092', value_serializer=lambda x: dumps(x).encode('utf-8'), api_version = (0,10,1))

def create_reservation_message(correlative):
    data = {
    	"orderId": 1532,
    	"client": {
			"ruc": "17200140",
			"name": "Edinson Boada"
		},
		"articles": [
			{
				"code": correlative,
				"name": "name0001",
				"unitPrice": 15.0,
				"quantity": 3
			},
			{
				"code": f'{correlative}0',
				"name": "name0002",
				"unitPrice": 20.0,
				"quantity": 2
			}
		]
	}
    return data


for e in range(2):    
    correlative = str(27200140+e)
    message = create_reservation_message(correlative)   
    producer.send('inventory_management_topic', value=message)
    logging.info(message)
    sleep(0.1)
sleep(1)

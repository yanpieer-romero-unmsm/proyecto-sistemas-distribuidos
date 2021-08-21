# ripleycl-consumer-ops-coc-storage

## Instalación

- Instalar las dependencias
```
pip install -r requirements.txt
```

- Configurar las siguiente variables de entorno:

```
export KAFKA_SERVER=localhost
export KAFKA_PORT=9092
export KAFKA_POLL_TIMEOUT=1500
export KAFKA_POLL_MAXRECORDS=10000

export KAFKA_TOPIC=reservation_topic
export KAFKA_TOPIC_OUT=invoice_topic
```
Nota: Cambiar el valor por la ip del servidor donde se encuentra el kafka y el backend


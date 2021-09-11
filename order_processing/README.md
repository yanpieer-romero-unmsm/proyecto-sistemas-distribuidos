# API Order Processing
API asíncrona que consume información del tópico order_processing_topic, la procesa y la envía a la cola del
tópico inventory_management_topic.

Además, expone los artículos disponibles en base de datos.

## Pre requisitos
### Plugins

### Repositorios
* [Redis] Order

### Kafka
Ubicarse en el directorio de KAFKA_HOME
* Levantar Zookeeper:
  bin\windows\zookeeper-server-start.bat config\zookeeper.properties

* Levantar Apache Kafka:
  bin\windows\kafka-server-start.bat config\server.properties

* Crear tópico:
  bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic orderTopic

* Listar tópicos
  bin\windows\kafka-topics.bat --list --zookeeper localhost:2181

* Crear producer
  bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic oderTopic

* Crear consumer
  bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic orderTopic --from-beginning

## Ejecución del proyecto
$ go get 
$ go run main.go 

## Covertura de pruebas unitarias
No aplica.

## Despliegue
No aplica.
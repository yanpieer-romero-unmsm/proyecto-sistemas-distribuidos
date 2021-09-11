import Kafka from 'node-rdkafka';
import { ReceivableEntity } from '../entities/receivableEntity.js'

var receivableData = null;

var consumer = new Kafka.KafkaConsumer({
  'group.id': 'kafka',
  'metadata.broker.list': 'localhost:9092',
}, {});

consumer.connect();

consumer.on('ready', () => {
  console.log('consumer ready..')
  consumer.subscribe(['invoice_topic']);
  consumer.consume();
}).on('data', function(data) {
  console.log(`received message: ${data.value.toString()}`);
  if (data != null) {
    let receivable = JSON.parse(data.value.toString())
    let receivableEntity = new ReceivableEntity(receivable)
    receivableEntity.save_message()
    receivableEntity.send_message()
  }
});






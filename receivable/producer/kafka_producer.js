import Kafka from 'node-rdkafka';

export class Producer {    
  constructor() {
      this.stream = Kafka.Producer.createWriteStream({
        'metadata.broker.list': 'localhost:9092'
      }, {}, {
        topic: 'receivable_topic'
      });
  }

  queueRandomMessage(message) {
    const success = this.stream.write(JSON.stringify(message));     
    if (success) {
      console.log(`message queued (${JSON.stringify(message)})`);
    } else {
      console.log('Too many messages in the queue already..');
    }
  }
}

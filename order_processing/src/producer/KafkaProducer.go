package producer

import (
	"context"
	"fmt"
	"log"

	"github.com/segmentio/kafka-go"
)

func KafkaProducer(topic string, server string, message string) {

	writer := &kafka.Writer{
		Addr: kafka.TCP(server),
		Topic: topic,
	}

	fmt.Println("KAFKA PRODUCER ========== Starting producer")

	err := writer.WriteMessages(context.Background(), kafka.Message{
		Value: []byte(message),
	})

	if err != nil {
		log.Fatal("KAFKA PRODUCER ========== cannot send request: ", err)
	}

	fmt.Println("KAFKA PRODUCER ========== Sending a message to kafka: \n", string(message))
	
	writer.Close()
	
	fmt.Println("KAFKA PRODUCER ========== Finishing producer")
}
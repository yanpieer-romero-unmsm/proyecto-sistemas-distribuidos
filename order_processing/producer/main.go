package producer

import (
	"context"
	"fmt"
	"log"

	"github.com/segmentio/kafka-go"
)

func StartKafkaProducer(topic string, server string, message string) {

	writer := &kafka.Writer{
		Addr: kafka.TCP(server),
		Topic: topic,
	}

	fmt.Println("Starting producer")

	err := writer.WriteMessages(context.Background(), kafka.Message{
		Value: []byte(message),
	})

	if err != nil {
		log.Fatal("cannot send request: ", err)
	}

	fmt.Println("Sending a message to kafka: \n", string(message))
	
	writer.Close()
	
	fmt.Println("Finishing producer")
}
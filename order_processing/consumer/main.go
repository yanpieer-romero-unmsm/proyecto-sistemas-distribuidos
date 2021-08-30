package consumer

import (
	"context"
	"fmt"
	"log"

	"github.com/segmentio/kafka-go"
)

func StartKafkaConsumer(topic string, server string) {
	reader := kafka.NewReader(kafka.ReaderConfig{
		Brokers:  []string{"localhost:9092"},
		GroupID:  "consumer",
		Topic:    topic,
		MinBytes: 0,
		MaxBytes: 10e6, //10MB
	})

	fmt.Println("Starting consumer")

	for i := 0; i < 1; i++ {
		message, err := reader.ReadMessage(context.Background())
		if err != nil {
			log.Fatal("cannot receive a message: ", err)
			reader.Close()
		}
		fmt.Println("Retrieving a message from kafka: \n", string(message.Value))
	}
	reader.Close()

	fmt.Println("Finishing consumer")
}
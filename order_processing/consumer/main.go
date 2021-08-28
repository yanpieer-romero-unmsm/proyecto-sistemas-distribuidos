package consumer

import (
	"context"
	"fmt"
	"log"

	"github.com/segmentio/kafka-go"
)

func StartKafkaConsumer() {
	fmt.Println("Entrnado a consumer")
	reader := kafka.NewReader(kafka.ReaderConfig{
		Brokers:  []string{"localhost:9092"},
		GroupID:  "consumer",
		Topic:    "order_processing_topic",
		MinBytes: 0,
		MaxBytes: 10e6, //10MB
	})

	fmt.Println("Entrnado al for")
	for i := 0; i < 1; i++ {
		message, err := reader.ReadMessage(context.Background())
		fmt.Println("En el for")
		if err != nil {
			log.Fatal("cannot receive a message: ", err)
			reader.Close()
		} else {
			fmt.Println("valor")
		}

		fmt.Println("Retrieving a message from kafka: \n", string(message.Value))
	}
	fmt.Println("saliendo del consumer")
	reader.Close()
}
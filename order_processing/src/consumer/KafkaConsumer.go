package consumer

import (
	"context"
	"fmt"
	"log"

	"github.com/segmentio/kafka-go"
	"yanpieer.com/order_processing/db"
	"yanpieer.com/order_processing/util"
)

func KafkaConsumer(topic string, server string) {
	reader := kafka.NewReader(kafka.ReaderConfig{
		Brokers:  []string{server},
		GroupID:  "consumer",
		Topic:    topic,
		MinBytes: 0,
		MaxBytes: 10e6, //10MB
	})

	fmt.Println("KAFKA CONSUMER ========== Starting Consumer")

	for {
		message, err := reader.ReadMessage(context.Background())
		if err != nil {
			log.Fatal("KAFKA CONSUMER ========== Cannot receive a message: ", err)
			break
		}
		fmt.Println("KAFKA CONSUMER ========== Retrieving a message from kafka: \n", string(message.Value))
		db.ConnectingWithRedisPUT(util.SERVER_REDIS, string(message.Value))
	}
	reader.Close()
	fmt.Println("KAFKA CONSUMER ========== Finishing consumer")
}
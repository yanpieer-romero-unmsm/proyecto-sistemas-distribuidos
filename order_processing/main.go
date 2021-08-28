package main

import (
	"encoding/json"
	"fmt"
	"net/http"
	"reflect"
	"strconv"

	"github.com/gin-gonic/gin"
	"yanpieer.com/order_processing/consumer"
	"yanpieer.com/order_processing/db"
	"yanpieer.com/order_processing/model"
	"yanpieer.com/order_processing/producer"
)


var articles = []model.Article{
	{ ID: 1, Name: "Lapicero", UnitPrice: 2.5, Quantity: 1 },
	{ ID: 2, Name: "Regla", UnitPrice: 3.5, Quantity: 1 },
	{ ID: 3, Name: "Mochila", UnitPrice: 2.5, Quantity: 1 },
}

var clients = []model.Client{
	{ Ruc: 17200193, Name: "Yanpieer" },
	{ Ruc: 17200133, Name: "Miguel" },
	{ Ruc: 17200140, Name: "Paolo" },
}

var orders = []model.Order{
	{ 1, clients[0], articles }, 
	{ 2, clients[1], articles },
	{ 3, clients[2], articles },
}


func main() {
	router := gin.Default()
	router.GET("/orders", getOrders)
	router.GET("/orders/:id", getOrderByID)
	router.POST("/orders", postOrders)

	router.Run("localhost:8080")
	
/* 	producer.StartKafkaProducer("order-processing_topic", string(orders)) */

}


func getOrders(c *gin.Context) {
	fmt.Println("Starting consumer")
	consumer.StartKafkaConsumer()
	fmt.Println("Finishing consumer")
	c.IndentedJSON(http.StatusOK, orders)
}


func postOrders(c *gin.Context) {
	var newOrder model.Order

	if err := c.BindJSON(&newOrder); err != nil {
		return
	}

	orders = append(orders, newOrder)
	c.IndentedJSON(http.StatusCreated, newOrder)
	jsonE, _ := json.Marshal(newOrder)
	fmt.Println("Starting producer")
	producer.StartKafkaProducer("order_processing_topic", string(jsonE))
	fmt.Println("Finishing producer")
	/* log.Output(1, string(jsonE)) */

	fmt.Println("Starting to connect with redis")
	db.ConnectingWithRedis(newOrder)
	fmt.Println("Finishing to connect with redis")
}


func getOrderByID(c *gin.Context) {
	id := c.Param("id")
	
	i, err := strconv.ParseInt(id, 10, 64)
	if err != nil {
		panic(err)
	}
	fmt.Printf("Hello, %v with type %s!\n", i, reflect.TypeOf(i))
	for _, a := range orders {
		if a.OrderID ==  i{
			c.IndentedJSON(http.StatusOK, a)
			jsonE, _ := json.Marshal(orders[i])
			fmt.Println(string(jsonE))
			return
		}
	}
	c.IndentedJSON(http.StatusNotFound, gin.H{"message": "order not found"})
}

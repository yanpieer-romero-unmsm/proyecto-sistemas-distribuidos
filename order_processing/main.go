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
	"yanpieer.com/order_processing/util"
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
	{ OrderID: 1, Client: clients[0], Article: articles }, 
	{ OrderID: 2, Client: clients[1], Article: articles },
	{ OrderID: 3, Client: clients[2], Article: articles },
}


func main() {
	router := gin.Default()
	router.GET("/orders", getOrders)
	router.GET("/orders/:id", getOrderByID)
	router.POST("/orders", postOrders)

	router.Run(util.SERVER_LOCAL)
	
}


func getOrders(c *gin.Context) {
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

	producer.StartKafkaProducer(util.TOPIC, util.SERVER_KAFKA, string(jsonE))
	consumer.StartKafkaConsumer(util.TOPIC, util.SERVER_KAFKA)
	/* log.Output(1, string(jsonE)) */
	db.ConnectingWithRedis(util.SERVER_REDIS, newOrder)
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

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




var orders = []model.Order{
}

func main() {
	router := gin.Default()
	router.GET("/api-order-processing/orders", getOrders)
	router.GET("/api-order-processing/orders/:id", getOrderByID)
	router.POST("/api-order-processing/orders", postOrders)
	router.GET("/api-order-processing/receivables", getReceivables)

	router.Run(util.SERVER_LOCAL)
	
}


func getOrders(c *gin.Context) {
	c.IndentedJSON(http.StatusOK, orders)
}

func getReceivables(c *gin.Context) {
	//jsonE, _ := json.Marshal(receivables[0])
	//fmt.Println(string(jsonE))

	m := consumer.StartKafkaConsumer(util.TOPIC_RECEIVABLE, util.SERVER_KAFKA)
	fmt.Println("String receivable :  ", m)
	var receivables model.Receivable
	json.Unmarshal([]byte(m), &receivables)
	fmt.Println("Elemento de receivable: ", receivables)
	db.ConnectingWithRedis(util.SERVER_REDIS, receivables)
	c.IndentedJSON(http.StatusOK, receivables)
}

func postOrders(c *gin.Context) {
	var newOrder model.Order

	if err := c.BindJSON(&newOrder); err != nil {
		return
	}

	orders = append(orders, newOrder)
	c.IndentedJSON(http.StatusCreated, newOrder)
	jsonE, _ := json.Marshal(newOrder)

	producer.StartKafkaProducer(util.TOPIC_ORDER, util.SERVER_KAFKA, string(jsonE))
	consumer.StartKafkaConsumer(util.TOPIC_ORDER, util.SERVER_KAFKA)
	/* log.Output(1, string(jsonE)) */
	//db.ConnectingWithRedis(util.SERVER_REDIS, newOrder)
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

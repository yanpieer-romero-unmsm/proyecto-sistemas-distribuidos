package db

import (
	"encoding/json"
	"fmt"
	"strconv"

	"github.com/go-redis/redis"
	"yanpieer.com/order_processing/model"
)

func ConnectingWithRedis(server string, obj model.Order) {
	fmt.Println("Starting redis")

	client := redis.NewClient(&redis.Options{
		Addr: server,
		Password: "",
		DB: 0,
	})

	json, err := json.Marshal(obj)
    if err != nil {
        fmt.Println(err)
    }

    err = client.Set(strconv.FormatInt(obj.OrderID, 10), json, 0).Err()
    if err != nil {
        fmt.Println(err)
    }

    val, err := client.Get(strconv.FormatInt(obj.OrderID, 10)).Result()
    if err != nil {
        fmt.Println(err)
    }
    fmt.Println(val)

	fmt.Println("Finishing redis")
}
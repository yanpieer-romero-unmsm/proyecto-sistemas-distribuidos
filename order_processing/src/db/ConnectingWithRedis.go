package db

import (
	"encoding/json"
	"fmt"

	"github.com/go-redis/redis"
	"github.com/satori/go.uuid"
	"yanpieer.com/order_processing/model"
)

func ConnectingWithRedisPUT(server string, m string){
	fmt.Println("REDIS PUT ========== Starting")

	clientRedis := redis.NewClient(&redis.Options{
		Addr: server,
		Password: "",
		DB: 0,
	})

	fmt.Println("REDIS PUT ========== struct enviado : ", m)
	myuuid := uuid.NewV4().String()
    err := clientRedis.Set(myuuid, m, 0).Err()
    if err != nil {
        fmt.Println("REDIS PUT ========== Error para setear: ", err)
    }
	fmt.Println("REDIS PUT ========== Finishing")
}

func ConnectingWithRedisGET(server string) []model.Receivable{
	fmt.Println("REDIS ========== Starting")

	clientRedis := redis.NewClient(&redis.Options{
		Addr: server,
		Password: "",
		DB: 0,
	})

	fmt.Println("REDIS GET ========== Obteniendo datos de redis")
    keys, err := clientRedis.Keys("*").Result()
    if err != nil {
        fmt.Println("Error para obtener: ", err)
    }
    fmt.Println("REDIS GET ========== Claves obtenido de redis: ", keys)
	fmt.Println("REDIS GET ========== Cantidad de claves: ", len(keys))
	receivable := model.Receivable{}
	receivables := []model.Receivable{}
	
	for i := 0; i < len(keys); i++ {
		result, _ := clientRedis.Get(keys[i]).Result()
		fmt.Println("REDIS GET ========== Mensaje de redis: ", result)
		json.Unmarshal([]byte(result), &receivable)
		fmt.Println("REDIS GET ========== Valor en la struct receivable: ", receivable)
		receivables = append(receivables, receivable)
		receivable.Articles = []model.Article{}
	}
	fmt.Println("REDIS GET ========== Valores en el arreglo receivables: ", receivables)
	fmt.Println("REDIS GET ========== Finishing")
	clientRedis.Close()
	
	return receivables[:]
}
package model

type Order struct {
    OrderID    int64      `json:"orderId"`  
    Client     Client     `json:"client"`
    Article    []Article  `json:"article"`
}
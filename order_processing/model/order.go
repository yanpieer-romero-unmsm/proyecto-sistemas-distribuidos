package model

type Order struct {
    ID         int64      `json:"id"`    
    OrderID    int64      `json:"orderId"`
    Client     Client     `json:"client"`
    Article    []Article  `json:"article"`
}
package model

type Article struct {
    ID     		int64  	`json:"id"`
    Name  		string  `json:"name"`
    UnitPrice 	float64 `json:"unitPrice"`
    Quantity  	int64 	`json:"quantity"`
}
package model

type Article struct {
    ID     		int64  	`json:"id"`
    Code        string  `json:"code"`
    Name  		string  `json:"name"`
    UnitPrice 	float64 `json:"unitPrice"`
    Quantity  	int64 	`json:"quantity"`
    Subtotal    float64 `json:"subtotal"`
}
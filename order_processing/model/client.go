package model

type Client struct {
    ID     	int64  	`json:"id"`
    Ruc    	string  `json:"ruc"`
    Name  	string  `json:"name"`
}
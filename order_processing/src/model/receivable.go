package model

type Receivable struct {
    OrderId    	  int64   	 `json:"orderId"`
	InvoiceId  	  string  	 `json:"invoiceId"`
	TotalIgv  	  string  	 `json:"totalIgv"`
	TotalInvoice  string  	 `json:"totalInvoice"`
	PaymentDate   string  	 `json:"paymentDate"`
	Client  	  Client  	 `json:"client"`
	Articles  	  []Article  `json:"articles"`
}
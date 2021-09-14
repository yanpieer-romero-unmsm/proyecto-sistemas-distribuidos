package model

type Receivable struct {
    OrderId    	  int64   	 `json:"orderId"`
	InvoiceId  	  string  	 `json:"invoiceId"`
	TotalIgv  	  int64  	 `json:"totalIgv"`
	TotalInvoice  int64  	 `json:"totalInvoice"`
	PaymentDate   string  	 `json:"paymentDate"`
	Client  	  Client  	 `json:"client"`
	Articles  	  []Article  `json:"articles"`
}
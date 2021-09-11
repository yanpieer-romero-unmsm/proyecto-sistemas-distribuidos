import { v4 as uuidv4 } from 'uuid';
import { post } from '../rest/RestUtils.js'
import { Producer } from '../producer/kafka_producer.js';
import { getShippingDate } from '../utils/utilitary_functions.js'

export class ReceivableEntity {
    
    constructor(receivable) {   
        this.order_id =  receivable['orderId']
        this.invoice_id = receivable['invoiceId'];        
        this.shipping_date = getShippingDate()
        this.total_invoice = receivable['invoiceTotal'];
        this.total_igv = receivable['igvTotal'];
        this.client = receivable['client'];
        this.articles = receivable['articles'];
    }

    save_message() {
        let receivableMessage = this.get_receivable_message()
        post(receivableMessage)
        console.log("Cuentas por cobrar guardadas!")
    }

    send_message() {
        const producer = new Producer()
        let receivableMessage = this.get_receivable_message_to_send()
        receivableMessage.orderId = this.order_id
        producer.queueRandomMessage(receivableMessage);
        console.log("Cuentas por cobrar enviadas al tópico!")
    }

    get_receivable_message() {
        let receivable = `{
            "receivable_id" : "${uuidv4()}",
            "invoice_id" : "${this.invoice_id}",        
            "total_invoice" : "${this.total_invoice}",
            "total_igv" : "${this.total_igv}"            
          }`;

        let jsonReceivable = JSON.parse(receivable);   

        jsonReceivable.client = this.client;
        jsonReceivable.articles = this.articles;

        return jsonReceivable;
    }

    get_receivable_message_to_send() {
        let receivable = `{
            "receivableId" : "${uuidv4()}",
            "invoiceId" : "${this.invoice_id}",        
            "totalInvoice" : "${this.total_invoice}",
            "totalIgv" : "${this.total_igv}",
            "paymentDate" : "${this.shipping_date}"
          }`;

        let jsonReceivable = JSON.parse(receivable);   

        jsonReceivable.client = this.client;
        jsonReceivable.articles = this.articles;

        return jsonReceivable;
    }
}
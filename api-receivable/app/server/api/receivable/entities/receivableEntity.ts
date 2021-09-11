export interface ReceivableEntity {
  receivable_id: string;
  invoice_id: string;
  payment_date: Date
  shipping_date: Date;
  total_invoice: number;
  total_igv: number;
  client: object;
  articles: object;
}

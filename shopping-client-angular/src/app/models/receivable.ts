import { Generic } from './generic';
import { Client } from './client';
import { Article } from './article';

export class Receivable implements Generic {
    id: number;
    orderId: number;
    invoiceId: number;
    totalIgv: number;
    totalInvoice: number;
    paymentDate: string;
    client: Client;
    articles: Article[];
}
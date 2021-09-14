import { Generic } from './generic';
import { Client } from './client';
import { Article } from './article';

export class Receivable implements Generic {
    id: number;
    orderId: number;
    invoiceId: string;
    totalIgv: string;
    totalInvoice: string;
    paymentDate: string;
    client: Client;
    articles: Article[];
}
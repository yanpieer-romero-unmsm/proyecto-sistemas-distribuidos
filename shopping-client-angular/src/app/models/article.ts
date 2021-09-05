import { Generic } from './generic';

export class Article implements Generic {
    id: number;
    code: string;
    name: string;
    unitPrince: number;
    quantity: number;
    subtotal: number;
}
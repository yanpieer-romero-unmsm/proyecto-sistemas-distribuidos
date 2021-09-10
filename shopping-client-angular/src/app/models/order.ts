import { Article } from "./article";
import { Client } from "./client";
import { Generic } from "./generic";

export class Order implements Generic{
    id: number;
    client: Client;
    article: Article[] = [];
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Article } from '../models/article';
import { Client } from '../models/client';
import { Order } from '../models/order';
import { CommonService } from './common.service';

@Injectable({
  providedIn: 'root'
})
export class OrdersService extends CommonService<Order>{

  protected baseEndpoint = '/api/orders';
  client: Client;
  order: Order;

  constructor(http: HttpClient) { 
    super(http);
  }
  
  login(user: Client) {
    this.client = new Client();
    this.client.id = user.id;
    this.client.name = user.name;
    this.client.ruc = user.ruc;
  }

  payArticle(article: Article) {
    this.order = new Order();
    this.order.id = Math.floor(Math.random() * 1000) + 1;
    this.order.client = this.client;
    this.order.article[0] = article;
    console.log(this.order);
    this.crear(this.order).subscribe();
  }
}
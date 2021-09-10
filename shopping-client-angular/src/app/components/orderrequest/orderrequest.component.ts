import { Component, OnInit } from '@angular/core';
import { OrdersService } from 'src/app/services/orders.service';
import { Article } from '../../models/article';
import { ArticlesService } from '../../services/articles.service';

@Component({
  selector: 'app-orderrequest',
  templateUrl: './orderrequest.component.html',
  styleUrls: ['./orderrequest.component.css']
})
export class OrderrequestComponent implements OnInit {
  ruc: string = '';
  articles: Article [];
  quantity: number[] = [];

  constructor(private articleService: ArticlesService, private orderService: OrdersService) { }

  ngOnInit(): void {
    this.articleService.listar()
    .subscribe(articles => this.articles = articles);
  }

  pagar(idArticle: string, stock: number, indexArray: number): void {
    var available: number = Number(this.quantity[indexArray]);
    if(stock<available) {
      window.alert("No hay la cantidad solicitada");
    } else {
  
      this.articles[indexArray].quantity = available;
      console.log(this.articles[indexArray]);
      this.orderService.payArticle(this.articles[indexArray]);
      window.alert('Se realizó el pago');
    }
  }

}

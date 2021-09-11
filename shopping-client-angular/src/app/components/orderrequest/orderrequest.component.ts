import { Component, OnInit } from '@angular/core';
import { OrdersService } from 'src/app/services/orders.service';
import { Article } from '../../models/article';
import { ArticlesService } from '../../services/articles.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-orderrequest',
  templateUrl: './orderrequest.component.html',
  styleUrls: ['./orderrequest.component.css']
})
export class OrderrequestComponent implements OnInit {
  ruc: string = '';
  articles: Article [];
  quantities: number[] = [];

  constructor(private articleService: ArticlesService, private orderService: OrdersService) { }

  ngOnInit(): void {
    this.articleService.listar()
    .subscribe(articles => this.articles = articles);
  }

  pagar(idArticle: string, stock: number, indexArray: number): void {
    var quantity: number = Number(this.quantities[indexArray]);
    if(stock < quantity) {
      Swal.fire('La orden no pudo ser procesada: ',
      'No contamos con esta cantidad de productos',
      'error');
    } else {
      this.articles[indexArray].quantity = quantity;
      this.articles[indexArray].subtotal = 0;
      this.orderService.payArticle(this.articles[indexArray]);
      Swal.fire('Orden pagada: ',
      'Orden pagada con éxito',
      'success');
      this.refresh();
    }
  }

  refresh(): void { 
    this.articleService.listar().subscribe(articles => {
      this.articles = articles;
    });
  }
}

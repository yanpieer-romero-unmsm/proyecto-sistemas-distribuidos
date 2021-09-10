import { Component, OnInit } from '@angular/core';
import { Article } from '../../models/article';
import { ArticlesService } from '../../services/articles.service';

@Component({
  selector: 'app-orderrequest',
  templateUrl: './orderrequest.component.html',
  styleUrls: ['./orderrequest.component.css']
})
export class OrderrequestComponent implements OnInit {
  ruc: string = '';
  articles: Article[];
  quantity: number[] = [];

  constructor(private articleService: ArticlesService) { }

  ngOnInit(): void {
    this.articleService.listar()
    .subscribe(articles => this.articles = articles);
  }

  isDisabled(stock: number, indexArray: number): boolean {
    var num: number = Number(this.quantity[indexArray-1]);
    if(stock<num){
      console.log("TRUE------------------");
      return true;
    }
    console.log("FALSE------------------");
    return false;
  }

  pagar(idArticle: string, stock: number, indexArray: number): void {
    var available: number = Number(this.quantity[indexArray]);
    if(stock<available) {
      window.alert("No hay la cantidad solicitada");
    } else {
      console.log("Se realizó el pago");
    }

  }

}

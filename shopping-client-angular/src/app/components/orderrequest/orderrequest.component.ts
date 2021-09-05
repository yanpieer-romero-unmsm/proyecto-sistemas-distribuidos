import { Component, OnInit } from '@angular/core';
import { Article } from '../../models/article';
import { ArticlesService } from '../../services/articles.service';

@Component({
  selector: 'app-orderrequest',
  templateUrl: './orderrequest.component.html',
  styleUrls: ['./orderrequest.component.css']
})
export class OrderrequestComponent implements OnInit {
  articles: Article[];

  constructor(private articleService: ArticlesService) { }

  ngOnInit(): void {
    this.articleService.listar()
    .subscribe(articles => this.articles = articles);
  }

}

import { Injectable } from '@angular/core';
import { CommonService } from './common.service';
import { Article } from '../models/article';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ArticlesService extends CommonService<Article>{

  protected baseEndpoint = '/api-inventory-management/articles';

  constructor(http: HttpClient) { 
    super(http);
  }
  
}
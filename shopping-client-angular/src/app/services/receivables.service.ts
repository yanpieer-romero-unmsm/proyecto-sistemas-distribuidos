import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Receivable } from '../models/receivable';
import { CommonService } from './common.service';

@Injectable({
  providedIn: 'root'
})
export class ReceivablesService extends CommonService<Receivable>{

  protected baseEndpoint = '/api/recevibales';

  constructor(http: HttpClient) { 
    super(http);
  }

}

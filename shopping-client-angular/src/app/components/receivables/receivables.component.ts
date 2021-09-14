import { Component, OnInit } from '@angular/core';
import { Receivable } from '../../models/receivable';
import { ReceivablesService } from '../../services/receivables.service';

@Component({
  selector: 'app-receivables',
  templateUrl: './receivables.component.html',
  styleUrls: ['./receivables.component.css']
})
export class ReceivablesComponent implements OnInit {

  receivables: Receivable[];

  constructor(private receivableService: ReceivablesService) { }

  ngOnInit(): void {
    
    this.receivableService.listar()
      .subscribe(receivables => this.receivables = receivables);
      console.log(this.receivables);  
  }
  
  

}

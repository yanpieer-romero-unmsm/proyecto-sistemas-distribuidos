import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Client } from 'src/app/models/client';
import { OrdersService } from 'src/app/services/orders.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  client: Client;

  constructor(private order:OrdersService, private router: Router) {
    this.client = new Client();
  }

  ngOnInit(): void {
  }

  login() {
    
    if(this.client.ruc==null||this.client.name==null){
      alert('Error login');
      return;
    }
    this.client.id = Math.floor(Math.random() * 1000) + 1;
    console.log(this.client)
    this.order.login(this.client);
    this.router.navigate(['/content']);
  }

}

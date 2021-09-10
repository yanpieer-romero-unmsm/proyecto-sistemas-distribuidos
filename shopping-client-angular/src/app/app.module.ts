import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OrderrequestComponent } from './components/orderrequest/orderrequest.component';
import { HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './utils/navbar/navbar.component';
import { UtilsModule } from './utils/utils.module';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import {MatCardModule} from '@angular/material/card';
import { ReceivablesComponent } from './components/receivables/receivables.component';
import { LoginComponent } from './components/login/login.component';
import { ContentComponent } from './components/content/content.component';

@NgModule({
  declarations: [
    AppComponent,
    OrderrequestComponent,
    ReceivablesComponent,
    LoginComponent,
    ContentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    UtilsModule,
    NoopAnimationsModule,
    FormsModule,
    MatCardModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

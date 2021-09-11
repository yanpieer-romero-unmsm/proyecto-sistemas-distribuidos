import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ContentComponent } from './components/content/content.component';
import { LoginComponent } from './components/login/login.component';
import { OrderrequestComponent } from './components/orderrequest/orderrequest.component';
import { ReceivablesComponent } from './components/receivables/receivables.component';


const routes: Routes = [

  { path: '', pathMatch: 'full', redirectTo: 'login' },
  { path: 'login', component: LoginComponent },
  { path: 'content', component: ContentComponent, children: [
      { path: '', component: OrderrequestComponent, outlet: 'cuerpo' },
      { path: 'articulos', component: OrderrequestComponent, outlet: 'cuerpo' },
      { path: 'receivables', component: ReceivablesComponent, outlet: 'cuerpo' }
  ]}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

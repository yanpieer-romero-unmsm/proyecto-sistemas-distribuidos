import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OrderrequestComponent } from './components/orderrequest/orderrequest.component';


const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'solicitud'},
  {path: 'solicitud', component: OrderrequestComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

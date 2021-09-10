import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OrderrequestComponent } from './components/orderrequest/orderrequest.component';
import { ReceivablesComponent } from './components/receivables/receivables.component';


const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'articulos' },
  { path: 'articulos', component: OrderrequestComponent },
  { path: 'ordenes', component: ReceivablesComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListComponent } from './pages/list/list.component';
import { FormComponent } from './pages/form/form.component';
import { FormGuard } from '../core/guards/form.guard';


const routes: Routes = [
  {
    path: "",
    component: ListComponent,
  },
  {
    path: "form",
    children: [
      {
        path: "",
        canDeactivate: [FormGuard],
        component: FormComponent,
      },
      {
        path: ":id",
        canDeactivate: [FormGuard],
        component: FormComponent,
      }
    ]
  },
];
@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PizzaRoutingModule { }

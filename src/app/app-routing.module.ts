import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {AdmineTemplateComponent} from "./admine-template/admine-template.component";

const routes: Routes = [
  {path : "" , component : LoginComponent},
  {path : "" , component : AdmineTemplateComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AdmineTemplateComponent } from './admine-template/admine-template.component';

const routes: Routes = [
  { path: '', component: AdmineTemplateComponent },  // Default route
  { path: 'login', component: LoginComponent }       // Login route
  // Add other routes here if needed
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


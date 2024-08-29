import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AdmineTemplateComponent } from './admine-template/admine-template.component';
import { DashboardComponent } from './dashboard/dashboard.component';
// Import other components as needed


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: '',
    component: AdmineTemplateComponent,
    children: [
      { path: 'dashboard', component: DashboardComponent },
      // Add other child routes here
    ]
  },
  { path: '**', redirectTo: 'dashboard', pathMatch: 'full' } // Redirect to 'dashboard' by default
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }



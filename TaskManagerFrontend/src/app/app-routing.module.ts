import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { TaskComponent } from './task/task.component';
import { TaskFormsComponent } from './task-forms/task-forms.component';
import { AuthGuard } from './auth/auth.guard';
import { TaskSearchComponent } from './task-search/task-search.component';

const routes: Routes = [
  {path: '', redirectTo:'login', pathMatch: 'full'},
  {path:'login', component: LoginComponent},
  {path:'inicio', component: TaskComponent, canActivate:[AuthGuard]},
  {path:'adicionar', component: TaskFormsComponent, canActivate:[AuthGuard]},
  {path:'buscar', component: TaskSearchComponent, canActivate:[AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }

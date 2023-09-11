import { NgModule } from '@angular/core';
import { BrowserModule, } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { TaskComponent } from './task/task.component';
import { NgbCollapseModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './header/header.component';
import { TaskFormsComponent } from './task-forms/task-forms.component';
import { RequestInterceptorProvider } from './interceptors/request.interceptor';
import { CookieService } from 'ngx-cookie-service';
import { TaskSearchComponent } from './task-search/task-search.component';
import { ErrorInterceptorProvider } from './interceptors/error.interceptor';
import { UsuarioFormsComponent } from './usuario-forms/usuario-forms.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    TaskComponent,
    HeaderComponent,
    TaskFormsComponent,
    TaskSearchComponent,
    UsuarioFormsComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule,
    NgbCollapseModule,
  ],
  providers: [
    RequestInterceptorProvider,
    CookieService,
    ErrorInterceptorProvider
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

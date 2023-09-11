import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private url = 'http://localhost:8030/api/taskmanager/authentication'

  constructor(private http: HttpClient, private cookieService: CookieService) { }

  get isLoggedIn(): boolean{
    const token = this.getToken;

    if(token){
      return true
    }
    return false
  }

  get getToken(){
    return this.cookieService.get('AUTH_TOKEN')
  }

  login(login: any){
    let url;
    
    url = this.url+"/login";

    const res = this.http.post<any>(url, login, { withCredentials: false }).pipe(
      map((res) => {
        this.logout()

        this.setCookie('AUTH_TOKEN', res.data.access_token);

        return res;

      })
    );

    return res;
  }

  setCookie(key: string, value: string){
    this.cookieService.set(key, value, {
      expires: this.setCookieExpireDate(),
      secure: true
    });
  }

  setCookieExpireDate(){
    const date: Date = new Date()
    date.setMinutes(date.getMinutes()+60)
    return date
  }

  logout() {
    this.cookieService.delete('AUTH_TOKEN')
  }
}

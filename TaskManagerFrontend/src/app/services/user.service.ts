import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url = '/api/taskmanager/users'

  constructor(private http: HttpClient) { }

  signUp(user: any){
    let url;
    
    url = this.url+"/signup";

    const res = this.http.post<any>(url, user).pipe(
      map((res) => {
        return res;

      })
    );

    return res;
  }
}

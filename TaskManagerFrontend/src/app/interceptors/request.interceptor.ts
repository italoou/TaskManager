import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AuthenticationService } from "../services/authentication.service";

@Injectable()
export class RequestInterceptor implements HttpInterceptor {

    constructor(private authenticationService: AuthenticationService){}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        
        const token = this.authenticationService.getToken

        if(!req.url.endsWith("login")){
            if(token){
                req = req.clone({
                    setHeaders:{
                        Authorization: "Bearer " + token
                    }
                })
            }
        }

        return next.handle(req);
    }
}

export const RequestInterceptorProvider = {
    provide: HTTP_INTERCEPTORS,
    useClass: RequestInterceptor,
    multi: true,
};
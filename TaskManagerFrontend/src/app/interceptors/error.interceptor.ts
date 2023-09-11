import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { catchError, Observable, throwError } from "rxjs";
import { AuthenticationService } from "../services/authentication.service";

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
    
    public constructor(private router: Router, private authenticationService: AuthenticationService){}
    
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {        
        return next.handle(req).pipe(
            catchError((resposta)=>{
                switch(resposta.status){
                    case 401:{
                        this.authenticationService.logout()
                        break;
                    }
                    case 403:{
                        this.router.navigate(["/"]);
                        break;
                    }
                    case 502:{
                        this.router.navigate(["/"]);
                        break;
                    }
                }

                let retorno = {
                    campos: {},
                    mensagens: []
                };

                if (resposta.error && resposta.error.data) {
                    if (resposta.error.data.campos) {
                        retorno.campos = resposta.error.data.campos;
                    }
                    if (resposta.error.data.mensagens) {
                        retorno.mensagens = resposta.error.data.mensagens;
                    }
                }

                return throwError(() => resposta);
            })
        )
    }

}

export const ErrorInterceptorProvider = {
    provide: HTTP_INTERCEPTORS,
    useClass: ErrorInterceptor,
    multi: true,
};
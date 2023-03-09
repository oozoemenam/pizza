import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';


@Injectable()
export class HeaderInterceptor implements HttpInterceptor {
    intercept(httpRequest: HttpRequest<any>, next: HttpHandler):
        Observable<HttpEvent<any>> {
            const Authorization = localStorage.getItem('token')
                ? `Bearer ${localStorage.getItem('token')}` : '';
            if (httpRequest.url.includes('api/v1'))
                return next.handle(httpRequest.clone({ setHeaders: { Authorization }}));
            else 
                return next.handle(httpRequest);
        }
}
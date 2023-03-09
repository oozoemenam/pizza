import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Pizza } from '../models/pizza.interface';

@Injectable({
  providedIn: 'root'
})
export class PizzaService {

  constructor(private http: HttpClient) { }

  getPizzas(): Observable<Pizza[]> {
    return this.http.get<Pizza[]>(`${environment.apiURL}/pizzas`).pipe(
      tap((data: Pizza[]) => data),
      catchError(err => throwError(() => err))
    )
  }

  getPizza(id: string): Observable<Pizza> {
    return this.http.get<Pizza>(`${environment.apiURL}/pizzas/${id}`).pipe(
      tap((data: Pizza) => data),
      catchError(err => throwError(() => err))
    )
  }

  addPizza(pizza: Pizza): Observable<Pizza> {    
    return this.http.post<Pizza>(`${environment.apiURL}/pizzas`, { ...pizza, orders: [] }).pipe(
      tap((data: Pizza) => data),
      catchError(err => throwError(() => err))
    )
  }

  updatePizza(id: string, pizza: Pizza): Observable<Pizza> {
    return this.http.put<Pizza>(`${environment.apiURL}/pizzas/${id}`, pizza).pipe(
      catchError(err => throwError(() => err))
    )
  }

  removePizza(id: string): Observable<Pizza> {
    return this.http.delete<Pizza>(`${environment.apiURL}/pizzas/${id}`).pipe(
      catchError(err => throwError(() => err))
    )
  }
}

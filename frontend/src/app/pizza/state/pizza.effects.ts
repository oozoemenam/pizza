import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Actions, createEffect, ofType } from "@ngrx/effects";
import { catchError, EMPTY, forkJoin, map, mergeMap, tap } from "rxjs";
import { Pizza } from "../models/pizza.interface";
import { PizzaService } from "../services/pizza.service";
import { PizzaActions } from "./pizza.actions";


@Injectable()
export class PizzaEffects {
    constructor(
        private actions$: Actions,
        private pizzaService: PizzaService,
        private router: Router
    ) {}

    getPizzas$ = createEffect(() => {
        return this.actions$.pipe(
            ofType(PizzaActions.GET_PIZZA_LIST),
            mergeMap(() => this.pizzaService.getPizzas()
                .pipe(
                    map(pizzas => ({ type: PizzaActions.SET_PIZZA_LIST, pizzas })),
                    catchError(() => EMPTY)
                ))
        )
    }, {dispatch: true});

    addPizza$ = createEffect(() => {
        return this.actions$.pipe(
            ofType(PizzaActions.ADD_PIZZA_API),
            mergeMap((data: { type: string, payload: Pizza }) => 
                this.pizzaService.addPizza(data.payload)
                .pipe(
                    map(pizzas => ({
                        type: PizzaActions.ADD_PIZZA_STATE,
                        pizza: data.payload
                    })),
                    tap(() => 
                        this.router.navigate(["pizza"])),
                    catchError(() => EMPTY)
                ))
        )
    }, { dispatch: true })

    updatePizza$ = createEffect(() => {
        return this.actions$.pipe(
            ofType(PizzaActions.UPDATE_PIZZA_API),
            mergeMap((data: { type: string, payload: Pizza }) => 
                this.pizzaService.updatePizza(data.payload.id, data.payload)
                .pipe(
                    map(pizzas => ({
                        type: PizzaActions.UPDATE_PIZZA_STATE,
                        pizza: data.payload
                    })),
                    tap(() => 
                        this.router.navigate(["pizza"])),
                    catchError(() => EMPTY)
                ))
        )
    }, { dispatch: true })

    removePizza$ = createEffect(() => {
        return this.actions$.pipe(
            ofType(PizzaActions.REMOVE_PIZZA_API),
            mergeMap((data: { type: string, payload: string }) => 
                this.pizzaService.removePizza(data.payload)
                .pipe(
                    map(() => ({
                        type: PizzaActions.REMOVE_PIZZA_STATE,
                        pizzaId: data.payload
                    })),
                    catchError(() => EMPTY)
                ))
        )
    }, { dispatch: true });

    removeAllPizza$ = createEffect(() => {
        return this.actions$.pipe(
            ofType(PizzaActions.REMOVE_ALL_PIZZA_API),
            mergeMap((data: { type: string, payload: string[] }) =>
                forkJoin([ ...data.payload.map((id) => this.pizzaService.removePizza(id))])
                .pipe(
                    map(() => ({ type: PizzaActions.REMOVE_ALL_PIZZA_STATE})),
                    catchError(() => EMPTY)
                ))
        )
    }, { dispatch: true })
}
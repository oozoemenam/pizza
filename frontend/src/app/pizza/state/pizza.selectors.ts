import { createFeatureSelector, createSelector } from "@ngrx/store";
import { PizzaState } from "./pizza.reducer";


export const selectPizzaState = 
createFeatureSelector<PizzaState>("pizzaState")

export const selectPizzas = () => createSelector(
    selectPizzaState,
    (state: PizzaState) => state.pizzas
)

export const selectPizza = (id: string) => createSelector(
    selectPizzaState,
    (state: PizzaState) => state.pizzas.find(pizza => pizza.id == id)
)
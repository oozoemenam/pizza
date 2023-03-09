import { createReducer, on } from "@ngrx/store";
import { Pizza } from "../models/pizza.interface";
import { addPizzaState, removeAllPizzaState, removePizzaState, setPizzaList, updatePizzaState } from "./pizza.actions";

export interface PizzaState {
    pizzas: ReadonlyArray<Pizza>;
}

export const initialState: PizzaState = {
    pizzas: []
}

export const pizzaReducer = createReducer(
    initialState,
    on(setPizzaList, (state, { pizzas }) => {
        return { 
            ...state, 
            pizzas 
        }
    }),
    on(addPizzaState, (state, { pizza }) => {
        return {
            ...state,
            pizzas: state.pizzas.concat(pizza)
        }
    }),
    on(updatePizzaState, (state, { pizza }) => {
        return {
            ...state,
            pizzas: state.pizzas.map(p => p.id === pizza.id ? pizza : p)
        }
    }),
    on(removePizzaState, (state, { pizzaId }) => {
        return { 
            ...state, 
            pizzas: state.pizzas.filter(pizza => pizza.id != pizzaId) 
        }
    }),
    on(removeAllPizzaState, (state) => {
        return { 
            ...state, 
            pizzas: [] 
        }
    }),
);
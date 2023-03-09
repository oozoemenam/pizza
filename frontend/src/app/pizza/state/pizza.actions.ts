import { createAction, props } from "@ngrx/store";
import { Pizza } from "../models/pizza.interface";

export enum PizzaActions {
    GET_PIZZA_LIST = '[Pizza] Get Pizza list',
    SET_PIZZA_LIST = '[Pizza] Set Pizza list',
    ADD_PIZZA_STATE = '[Pizza] Add Pizza (STATE)',
    ADD_PIZZA_API = '[Pizza] Add Pizza (API)',
    UPDATE_PIZZA_STATE = '[Pizza] Update Pizza (STATE)',
    UPDATE_PIZZA_API = '[Pizza] Update Pizza (API)',
    REMOVE_PIZZA_STATE = '[Pizza] Remove Pizza (STATE)',
    REMOVE_PIZZA_API = '[Pizza] Remove Pizza (API)',
    REMOVE_ALL_PIZZA_STATE = '[Pizza] Remove All Pizza (STATE)',
    REMOVE_ALL_PIZZA_API = '[Pizza] Remove All Pizza (API)',
}

export const getPizzaList = createAction(
    PizzaActions.GET_PIZZA_LIST,
);

export const setPizzaList = createAction(
    PizzaActions.SET_PIZZA_LIST,
    props<{ pizzas: ReadonlyArray<Pizza> }>(),
);

export const addPizzaState = createAction(
    PizzaActions.ADD_PIZZA_STATE,
    props<{ pizza: Pizza }>()
);

export const updatePizzaState = createAction(
    PizzaActions.UPDATE_PIZZA_STATE,
    props<{ pizza: Pizza }>()
);

export const removePizzaState = createAction(
    PizzaActions.REMOVE_PIZZA_STATE,
    props<{ pizzaId: string }>()
);

export const removeAllPizzaState = createAction(
    PizzaActions.REMOVE_ALL_PIZZA_STATE,
);

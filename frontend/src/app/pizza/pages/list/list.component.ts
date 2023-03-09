import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { AppState } from 'src/app/state/app.state';
import { CommandBarActions } from '../../enums/command-bar-actions.enum';
import { TableActions } from '../../enums/table-actions.enum';
import { Pizza } from '../../models/pizza.interface';
import { PizzaActions } from '../../state/pizza.actions';
import { selectPizzas } from '../../state/pizza.selectors';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {
  pizzas: ReadonlyArray<Pizza> = [
    {
      id: "1",
      name: "Margherita",
      size: "medium",
      price: 8.00
    }
  ];
  pizzas$ = this.store.select(selectPizzas());
  headers: { headerName: string, fieldName: keyof Pizza }[] = [
    { headerName: "Name", fieldName: "name" },
    { headerName: "Size", fieldName: "size" },
    { headerName: "Price", fieldName: "price" },
  ]
  constructor(
    private router: Router,
    private store: Store<AppState>,
  ) {}
  ngOnInit(): void {
    this.store.dispatch({ type: PizzaActions.GET_PIZZA_LIST });
    this.assignPizzas();
  }
  assignPizzas() {
    this.pizzas$.subscribe((data) => {
      this.pizzas = data;
    });
  }
  selectPizza(data: { pizza: Pizza, action: TableActions }) {
    switch (data.action) {
      case TableActions.View: {
        this.router.navigate(["pizza", "form", data.pizza.id]);
        return;
      }
      case TableActions.Delete: {
        this.store.dispatch({ 
          type: PizzaActions.REMOVE_PIZZA_API, 
          payload: data.pizza.id 
        });
        return;
      }
      default:
        return;
    }
  }

  executeCommandBarAction(action: CommandBarActions) {
    switch (action) {
      case CommandBarActions.Create: {
        this.router.navigate(["pizza", "form"]);
        return;
      }
      case CommandBarActions.DeleteAll: {
        this.store.dispatch({
          type: PizzaActions.REMOVE_ALL_PIZZA_API,
          payload: [ ...this.pizzas.map(p => p.id)]
        });
        return;
      }
      default: {
        return;
      }
    }
  }
}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { CanComponentDeactivate } from 'src/app/core/guards/form.guard';
import { AppState } from 'src/app/state/app.state';
import { Pizza } from '../../models/pizza.interface';
import { PizzaActions } from '../../state/pizza.actions';
import { selectPizza } from '../../state/pizza.selectors';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit, CanComponentDeactivate {
  pizza$: Observable<Pizza | undefined>;
  pizza: Pizza | null = null;
  constructor(
    private router: ActivatedRoute,
    private store: Store<AppState>
  ) {
    const id = this.router.snapshot.params['id'];
    this.pizza$ = this.store.select(selectPizza(id));
    this.pizza$.subscribe(data => {
      console.log('obs pizza data', data, id);
      
      if (data) this.pizza = data;
    });
  }
  ngOnInit(): void {
  }
  canDeactivate(): boolean | Observable<boolean> | Promise<boolean> {
    const confirmation = window.confirm('Are you sure?');
    return confirmation;
  }
  formAction(data: { value: Pizza, action: string }) {
    switch (data.action) {
      case "Create": {
        this.store.dispatch({
          type: PizzaActions.ADD_PIZZA_API,
          payload: data.value
        });
        return;
      }
      case "Update": {
        this.store.dispatch({
          type: PizzaActions.UPDATE_PIZZA_API,
          payload: data.value
        });
        return;
      }
      default:
        return;
    }
  }
}

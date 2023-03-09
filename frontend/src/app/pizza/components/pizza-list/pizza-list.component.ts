import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { TableActions } from '../../enums/table-actions.enum';
import { Pizza } from '../../models/pizza.interface';

@Component({
  selector: 'app-pizza-list',
  templateUrl: './pizza-list.component.html',
  styleUrls: ['./pizza-list.component.scss']
})

export class PizzaListComponent implements OnInit {
  @Input() headers: Array<{headerName: string, fieldName: keyof Pizza}> = [];
  @Input() pizzas: ReadonlyArray<Pizza> = [];
  @Output() pizza = new EventEmitter<{ pizza: Pizza, action: TableActions }>();
  headerFields: string[] = [];

  constructor() {}
  ngOnInit(): void {
    this.getHeaderFields();
  }
  getHeaderFields() {
    this.headerFields = this.headers.map((data) => data.fieldName);
    this.headerFields.push("actions");
  }
  selectPizza(pizza: Pizza, action: TableActions) {
    this.pizza.emit({ pizza, action });
  }
}

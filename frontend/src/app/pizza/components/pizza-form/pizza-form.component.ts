import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Pizza } from '../../models/pizza.interface';

@Component({
  selector: 'app-pizza-form',
  templateUrl: './pizza-form.component.html',
  styleUrls: ['./pizza-form.component.scss']
})
export class PizzaFormComponent implements OnInit {
  @Input() selectedPizza: Pizza | null = null;
  @Input() actionButtonLabel: string = 'Create';
  @Output() action = new EventEmitter();
  form: FormGroup;

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      id: [''],
      name: [''],
      size: [''],
      price: ['']
    })
  }

  ngOnInit(): void {
    this.checkAction();
  }

  checkAction() {
    if (this.selectedPizza) {
      this.actionButtonLabel = 'Update';
      this.patchDataValues();
    }
  }

  patchDataValues() {
    if(this.selectedPizza) {
      this.form.patchValue(this.selectedPizza);
    }
  }

  emitAction() {
    this.action.emit({ 
      value: this.form.value, 
      action: this.actionButtonLabel 
    });
  }

  clear() {
    this.form.reset();
  }
}


import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { CommandBarActions } from '../../enums/command-bar-actions.enum';

@Component({
  selector: 'app-pizza-command-bar',
  templateUrl: './pizza-command-bar.component.html',
  styleUrls: ['./pizza-command-bar.component.scss']
})
export class PizzaCommandBarComponent implements OnInit {
  @Output() action = new EventEmitter<CommandBarActions>()
  constructor() {}
  ngOnInit(): void {
    
  }
  emitAction(action: CommandBarActions) {
    this.action.emit(action);
  }
}

import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  @Output() actionEmitter = new EventEmitter();
  @Input() loggedIn = false;
  submit(action: string) {
    this.actionEmitter.emit(action);
  }
  ngOnInit(): void {
    
  }
}

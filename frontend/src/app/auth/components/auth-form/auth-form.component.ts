import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-auth-form',
  templateUrl: './auth-form.component.html',
  styleUrls: ['./auth-form.component.scss']
})
export class AuthFormComponent {
  @Input() error: string = "";
  @Input() title: string = "Login";
  @Output() submitEmitter = new EventEmitter();
  form: FormGroup;

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      email: [''],
      password: ['']
    })
  }

  submit() {
    this.submitEmitter.emit(this.form.value);
  }
}

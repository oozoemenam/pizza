import { Component, OnInit, OnDestroy } from '@angular/core';
import { Store } from '@ngrx/store';
import { Subscription } from 'rxjs';
import { User } from '../../models/user.interface';
import { AuthActions } from '../../state/auth.actions';
import { selectError } from '../../state/auth.selectors';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  error: string = "";

  constructor(private store: Store) {

  }

  ngOnInit(): void {
    
  }

  submit(data: User) {
    this.store.dispatch({ type: AuthActions.CREATE_USER, payload: data });
  }

  // getError() {
  //   this.errorSub = this.error$.subscribe(data => {
  //     if (data) {
  //       this._snackBar.open(data.message, "Error");
  //     }
  //   })
  // }
  // submit(data: User) {
  //   this.authService.register(data).subscribe((data) => {
  //     this.router.navigate(['/']);
  //   });
  // }
}

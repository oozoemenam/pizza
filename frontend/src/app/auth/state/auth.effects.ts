import { Router } from "@angular/router";
import { Injectable } from "@angular/core";
import { Actions, createEffect, ofType } from "@ngrx/effects";
import { catchError, map, mergeMap, tap } from "rxjs";
import { AuthenticateService } from "src/app/core/services/authenticate.service";
import { User } from "../models/user.interface";
import { AuthActions } from "./auth.actions";

@Injectable()
export class AuthEffects {
    constructor(
        private actions$: Actions,
        private authService: AuthenticateService,
        private router: Router
    ) {}

    loginUser$ = createEffect(() => {
        return this.actions$.pipe(
            ofType(AuthActions.LOGIN),
            mergeMap(((data: { type: string, payload: User }) =>
                this.authService.login(data.payload)
                .pipe(
                    map(data => ({
                        type: AuthActions.SET_TOKEN,
                        token: data.token
                    })),
                    tap(() =>
                        this.router.navigate(['pizza'])),
                    catchError(async (data) => ({
                        type: AuthActions.AUTH_ERROR,
                        error: data.error
                    }))
                ))
            ))
    }, { dispatch: true });

    createUser$ = createEffect(() => {
        return this.actions$.pipe(
            ofType(AuthActions.CREATE_USER),
            mergeMap(((data: { type: string, payload: User }) => 
                this.authService.register(data.payload)
                .pipe(
                    tap(() => this.router.navigate(['login'])),
                    catchError(async (data) => ({
                        type: AuthActions.AUTH_ERROR,
                        error: data.error
                    }))
                ))
            ))
    }, { dispatch: true });
}
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CoreModule } from '../core/core.module';
import { LoginComponent } from './page/login/login.component';
import { RegisterComponent } from './page/register/register.component';
import { ActionReducer, MetaReducer, StoreModule } from '@ngrx/store';
import { authReducer } from './state/auth.reducer';
import { EffectsModule } from '@ngrx/effects';
import { localStorageSync } from 'ngrx-store-localstorage';
import { AuthEffects } from './state/auth.effects';
import { AuthFormComponent } from './components/auth-form/auth-form.component';
import { AuthRoutingModule } from './auth-routing.module';


export function localStorageSyncReducer(
  reducer: ActionReducer<any>): ActionReducer<any> {
    return localStorageSync({ keys: ['token']})(reducer);
  }
const metaReducers: Array<MetaReducer<any, any>> = [localStorageSyncReducer];
@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    AuthFormComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    AuthRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    CoreModule,
    StoreModule.forFeature('authState', authReducer, { metaReducers }),
    EffectsModule.forFeature([AuthEffects]),
  ]
})
export class AuthModule { }

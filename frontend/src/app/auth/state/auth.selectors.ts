import { createFeatureSelector, createSelector } from "@ngrx/store";
import { AuthState } from "./auth.reducer";


export const selectAuthState = createFeatureSelector<AuthState>('authState')
export const selectError = () => createSelector(
    selectAuthState,
    (state: AuthState) => state.error
)
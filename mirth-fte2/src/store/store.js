// @ts-nocheck
import {
    configureStore,
} from "@reduxjs/toolkit";
import { authSliceReducer } from './auth-state'

export const store = configureStore({
    reducer: { auth: authSliceReducer },
});

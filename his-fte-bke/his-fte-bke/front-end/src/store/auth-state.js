// @ts-nocheck
import {
    createSlice,
    createAsyncThunk,
} from "@reduxjs/toolkit";
import { SERVER_URL } from "../constants";

const initialAuthState = {
    // isAuthenticated: !!sessionStorage.getItem("jwt"),
    isAuthenticated: true,

    loginFailed: false,
};

export const login = createAsyncThunk(
    "users/login",
    async (user, thunkAPI) => {
        try {
            const response = await fetch(SERVER_URL + "login", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(user),
            });
            if (response.status === 200) {
                const jwtToken = response.headers.get("Authorization");
                if (jwtToken !== null) {
                    return jwtToken;
                }
            } else {
                return thunkAPI.rejectWithValue(response.status);
            }
        } catch (error) {
            return thunkAPI.rejectWithValue(error);
        }
    }
);
const authSlice = createSlice({
    name: "authentication",
    initialState: initialAuthState,
    reducers: {
        logout(state) {
            sessionStorage.removeItem("jwt");
            state.isAuthenticated = false;            
        },
        resetLoginFailed(state){
            state.loginFailed = false;
        }
    },
    extraReducers: {
        [login.pending]: (state) => {
            state.isAuthenticated = false;
        },
        [login.rejected]: (state) => {
            state.isAuthenticated = false;
            state.loginFailed = true;
        },
        [login.fulfilled]: (state, {payload}) => {
            sessionStorage.setItem("jwt", payload);
            state.isAuthenticated = true;
        },
    },
});

export const authSliceReducer = authSlice.reducer;
export const {logout, resetLoginFailed} = authSlice.actions;

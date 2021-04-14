import {createSlice} from "@reduxjs/toolkit";

export const authSlice = createSlice({
    name: "auth",
    initialState: {
        logged: true
    },
    reducers: {
        login: (state) => {
            return {
                ...state,
                logged: true
            }
        },
        logout: (state) => {
            return {
                ...state,
                logged: false
            }
        },
    },
});
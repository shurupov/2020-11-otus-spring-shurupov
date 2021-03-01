import { createSlice } from "@reduxjs/toolkit";

export const apiSlice = createSlice({
    name: "api",
    initialState: {
        prefix: "/api/v1",
    },
    reducers: {
        update: (state, action) => {
            return {
                ...state,
                prefix: action.payload
            }
        },
    },
});
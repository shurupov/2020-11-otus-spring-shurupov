import { createSlice } from "@reduxjs/toolkit";

export const authorSlice = createSlice({
    name: "author",
    initialState: {
        list: [],
        element: {
            id: '1',
            name: "",
            firstName: "",
            lastName: "",
        },
    },
    reducers: {
        list: (state, action) => {
            return {
                ...state,
                list: action.payload
            }
        },
        get: (state, action) => {
            return {
                ...state,
                element: action.payload
            }
        },
    },
});
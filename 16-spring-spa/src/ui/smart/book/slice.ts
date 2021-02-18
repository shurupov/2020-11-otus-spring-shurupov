import { createSlice } from "@reduxjs/toolkit";

export const bookSlice = createSlice({
    name: "book",
    initialState: {
        list: [],
        element: {
            id: 0,
            name: "",
            genres: [],
            authorId: 0
        },
    },
    reducers: {
        list: (state, action) => {
            return {
                ...state,
                list: action.payload
            }
        },
        updateElementView: (state, action) => {
            return {
                ...state,
                element: action.payload
            }
        },
    },
});
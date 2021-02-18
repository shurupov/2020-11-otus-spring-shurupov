import { createSlice } from "@reduxjs/toolkit";

export const genreSlice = createSlice({
    name: "genre",
    initialState: {
        list: [],
        element: {
            id: '1',
            name: "",
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
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
        elementToDeleteId: null
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
        deleteElement: (state, action) => {
            return {
                ...state,
                elementToDeleteId: action.payload
            }
        },
    },
});
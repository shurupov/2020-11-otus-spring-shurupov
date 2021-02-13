import { createSlice } from "@reduxjs/toolkit";

export const bookSlice = createSlice({
    name: "book",
    initialState: {
        list: [],
        book: {
            id: '1',
            name: 'The Tale about Fisherman and a Gold Fish',
            genres: "Fairy Tale, Drama",
            author: "Alexander Pushkin"
        },
    },
    reducers: {
        list: (state, action) => {
            console.log("list", action);
            return {
                ...state,
                list: action.payload
            }
        },
    },
});
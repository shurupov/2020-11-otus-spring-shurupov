import {createSlice} from "@reduxjs/toolkit";

export const summarySlice = createSlice({
    name: "summary",
    initialState: {
        counts: {
            books: 0,
            comments: 0,
            authors: 0,
            genres: 0
        },
    },
    reducers: {
        setSummary: (state, action) => {
            return {
                ...state,
                counts: action.payload
            }
        },
    },
});
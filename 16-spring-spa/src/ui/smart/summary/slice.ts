import {createSlice} from "@reduxjs/toolkit";

export const summarySlice = createSlice({
    name: "summary",
    initialState: {
        counts: {
            books: 5,
            comments: 4,
            authors: 6,
            genres: 8
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
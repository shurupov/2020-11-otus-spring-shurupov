import {createSlice} from "@reduxjs/toolkit";

export const crumbsSlice = createSlice({
    name: "crumbs",
    initialState: {
        items: [
            {caption: "Home", url: "/"},
        ],
    },
    reducers: {
        setCrumbs: (state, action) => {
            return {
                ...state,
                items: action.payload
            }
        },
    },
});
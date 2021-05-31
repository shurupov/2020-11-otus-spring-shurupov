import {createSlice} from "@reduxjs/toolkit";

export const purchasesListSlice = createSlice({
    name: "purchasesList",
    initialState: {
        list: [],
    },
    reducers: {
        list: (state, action) => {
            return {
                ...state,
                list: action.payload
            }
        },
    }
});
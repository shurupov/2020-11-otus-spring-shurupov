import {createSlice} from "@reduxjs/toolkit";

export const productsListSlice = createSlice({
    name: "products",
    initialState: [],
    reducers: {
        list: (state, action) => {
            return action.payload.slice();
        }
    }
});
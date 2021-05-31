import {createSlice} from "@reduxjs/toolkit";
import {ProductProps} from "./Product";

export const productSlice = createSlice({
    name: "product",
    initialState: {
        id: 0,
        name: "",
        price: 0,
        description: "",
        createdAt: "",
        optionName: "",
        options: [],
        properties: {}
    } as ProductProps,
    reducers: {
        display: (state, action) => {
            return {
                ...state,
                ...action.payload
            }
        }
    }
});
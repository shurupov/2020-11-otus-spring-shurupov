import {createSlice} from "@reduxjs/toolkit";
import {PurchaseProps} from "./Purchase";

export const purchaseSlice = createSlice({
    name: "purchase",
    initialState: {
        id: 0,
        name: "",
        description: "",
        createdAt: "",
    } as PurchaseProps,
    reducers: {
        display: (state, action) => {
            return {
                ...state,
                ...action.payload
            }
        }
    }
});
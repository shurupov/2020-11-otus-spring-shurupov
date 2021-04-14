import {createSlice} from "@reduxjs/toolkit";

export const alertSlice = createSlice({
    name: "alert",
    initialState: {
        text: "",
        visible: false,
    },
    reducers: {
        show: (state, action) => {
            return {
                ...state,
                text: action.payload,
                visible: true,
            }
        },
        hide: (state) => {
            return {
                ...state,
                visible: false,
            }
        }
    },
});
import { createSlice } from "@reduxjs/toolkit";
import {EditorType} from "../../utils/EditorType";

export const authorSlice = createSlice({
    name: "author",
    initialState: {
        list: [],
        element: {
            id: '1',
            name: "",
            firstName: "",
            lastName: "",
        },
        elementToDeleteId: null,
        editorType: EditorType.EDIT,
    },
    reducers: {
        list: (state, action) => {
            return {
                ...state,
                list: action.payload
            }
        },
        openElement: (state, action) => {
            return {
                ...state,
                element: action.payload
            }
        },
        switchEditor: (state, action) => {
            return {
                ...state,
                editorType: action.payload,
            };
        },
        deleteElement: (state, action) => {
            return {
                ...state,
                elementToDeleteId: action.payload
            }
        },
    },
});
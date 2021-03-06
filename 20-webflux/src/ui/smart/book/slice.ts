import { createSlice } from "@reduxjs/toolkit";
import {EditorType} from "../../utils/EditorType";

export const bookSlice = createSlice({
    name: "book",
    initialState: {
        list: [],
        element: {
            id: 0,
            name: "",
            genres: [],
            comments: [],
            authorId: 0
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
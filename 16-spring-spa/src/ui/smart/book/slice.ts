import { createSlice } from "@reduxjs/toolkit";
import {EditorType} from "components/book/BookEditView";

export const bookSlice = createSlice({
    name: "book",
    initialState: {
        list: [],
        element: {
            id: 0,
            name: "",
            genres: [],
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
        updateElementView: (state, action) => {
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
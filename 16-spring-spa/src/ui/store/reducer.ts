import {combineReducers} from "redux";
import {bookSlice} from "smart/book/slice";

export const reducer = combineReducers({
    book: bookSlice.reducer
});

export type StoreState = ReturnType<typeof reducer>;
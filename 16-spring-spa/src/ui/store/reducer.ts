import {combineReducers} from "redux";
import { connectRouter } from "connected-react-router";
import {bookSlice} from "smart/book/slice";

export const createRootReducer = (history: any) => combineReducers({
    router: connectRouter(history),
    book: bookSlice.reducer,
});

export type StoreState = ReturnType<typeof createRootReducer>;
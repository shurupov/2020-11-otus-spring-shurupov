import {combineReducers} from "redux";
import {connectRouter} from "connected-react-router";
import {bookSlice} from "smart/book/slice";
import {authorSlice} from "smart/authors/slice";
import {genreSlice} from "smart/genres/slice";

export const createRootReducer = (history: any) => combineReducers({
    router: connectRouter(history),
    book: bookSlice.reducer,
    author: authorSlice.reducer,
    genre: genreSlice.reducer,
});
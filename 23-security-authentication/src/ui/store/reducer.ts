import {combineReducers} from "redux";
import {connectRouter} from "connected-react-router";
import {bookSlice} from "smart/book/slice";
import {authorSlice} from "smart/authors/slice";
import {genreSlice} from "smart/genre/slice";
import {crumbsSlice} from "smart/breadCrumbs/slice";
import {summarySlice} from "smart/summary/slice";
import {commentSlice} from "smart/comment/slice";
import {authSlice} from "smart/login/slice";

export const createRootReducer = (history: any) => combineReducers({
    router: connectRouter(history),
    crumbs: crumbsSlice.reducer,
    summary: summarySlice.reducer,
    book: bookSlice.reducer,
    comment: commentSlice.reducer,
    genre: genreSlice.reducer,
    author: authorSlice.reducer,
    auth: authSlice.reducer,
});
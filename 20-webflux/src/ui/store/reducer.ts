import {combineReducers} from "redux";
import {connectRouter} from "connected-react-router";
import {bookSlice} from "smart/book/slice";
import {authorSlice} from "smart/authors/slice";
import {crumbsSlice} from "smart/breadCrumbs/slice";
import {summarySlice} from "smart/summary/slice";
import {apiSlice} from "smart/apiSelect/slice";

export const createRootReducer = (history: any) => combineReducers({
    router: connectRouter(history),
    api: apiSlice.reducer,
    crumbs: crumbsSlice.reducer,
    summary: summarySlice.reducer,
    book: bookSlice.reducer,
    author: authorSlice.reducer,
});
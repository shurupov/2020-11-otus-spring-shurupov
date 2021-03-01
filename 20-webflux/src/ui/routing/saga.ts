import {put, takeEvery, select} from "redux-saga/effects";
import {sagaActionTypes} from "store/sagaActionTypes";
import {openBookListAction, openBookAction, openEmptyBookAction, removeBookAction} from "smart/book/saga";
import {bookSlice} from "smart/book/slice";
import {summaryAction} from "smart/summary/saga";
import {authorSlice} from "smart/authors/slice";
import {openAuthorAction, openAuthorListAction, openEmptyAuthorAction, removeAuthorAction} from "smart/authors/saga";

export const pathSelector = (state: any) => state.router.location.pathname;

export function* workerLocationChange() {
    const url = yield select(pathSelector);
    if (url == "/books") {
        yield put(openBookListAction());
    } else if (url == "/books/add") {
        yield put(openEmptyBookAction());
    } else if (/^\/books\/[\dabcdef]+$/.test(url)) {
        yield put(openBookAction());
    } else if (/^\/books\/([\dabcdef]+)\/delete$/.test(url)) {
        const result = url.match(/^\/books\/([\dabcdef]+)\/delete$/);
        const id = result[1];
        yield put(bookSlice.actions.deleteElement(id));
        yield put(removeBookAction());
    } else

    if (url == "/authors") {
        yield put(openAuthorListAction());
    } else if (url == "/authors/add") {
        yield put(openEmptyAuthorAction());
    } else if (/^\/authors\/[\dabcdef]+$/.test(url)) {
        yield put(openAuthorAction());
    } else if (/^\/authors\/([\dabcdef]+)\/delete$/.test(url)) {
        const result = url.match(/^\/authors\/([\dabcdef]+)\/delete$/);
        const id = result[1];
        yield put(authorSlice.actions.deleteElement(id));
        yield put(removeAuthorAction());
    } else

    if (url == "/") {
        yield put(summaryAction());
    }
}

export function* watchLocationChange() {
    yield takeEvery(sagaActionTypes.LOCATION_CHANGE, workerLocationChange);
}
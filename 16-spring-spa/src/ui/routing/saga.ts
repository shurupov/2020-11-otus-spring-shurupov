import {put, takeEvery, select} from "redux-saga/effects";
import {sagaActionTypes} from "store/sagaActionTypes";
import {openBookListAction, openBookAction, openEmptyBookAction, removeBookAction} from "smart/book/saga";
import {bookSlice} from "smart/book/slice";
import {summaryAction} from "smart/summary/saga";
import {openGenreListAction} from "smart/genres/saga";

export const pathSelector = (state: any) => state.router.location.pathname;

export function* workerLocationChange() {
    const url = yield select(pathSelector);
    if (url == "/books") {
        yield put(openBookListAction());
    } else if (url == "/books/add") {
        yield put(openEmptyBookAction());
    } else if (/^\/books\/\d+$/.test(url)) {
        yield put(openBookAction());
    } else if (/^\/books\/(\d+)\/delete$/.test(url)) {
        const result = url.match(/^\/books\/(\d+)\/delete$/);
        const id = result[1];
        yield put(bookSlice.actions.deleteElement(id));
        yield put(removeBookAction());
    } else

    if (url == "/genres") {
        yield put(openGenreListAction());
    } /*else if (url == "/genres/add") {
        yield put(openEmptyBookAction());
    } else if (/^\/genres\/\d+$/.test(url)) {
        yield put(openBookAction());
    } else if (/^\/genres\/(\d+)\/delete$/.test(url)) {
        const result = url.match(/^\/genres\/(\d+)\/delete$/);
        const id = result[1];
        yield put(bookSlice.actions.deleteElement(id));
        yield put(removeBookAction());
    }*/ else

    if (url == "/") {
        yield put(summaryAction());
    }
}

export function* watchLocationChange() {
    yield takeEvery(sagaActionTypes.LOCATION_CHANGE, workerLocationChange);
}
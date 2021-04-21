import {put, takeEvery, select} from "redux-saga/effects";
import {sagaActionTypes} from "../store/sagaActionTypes";
import {openBookListAction, openBookAction, openEmptyBookAction, removeBookAction} from "../smart/book/saga";
import {bookSlice} from "../smart/book/slice";
import {summaryAction} from "../smart/summary/saga";
import {openEmptyGenreAction, openGenreAction, openGenreListAction, removeGenreAction} from "../smart/genre/saga";
import {genreSlice} from "../smart/genre/slice";
import {authorSlice} from "../smart/authors/slice";
import {openAuthorAction, openAuthorListAction, openEmptyAuthorAction, removeAuthorAction} from "../smart/authors/saga";
import {
    openCommentAction,
    openEmptyCommentAction,
    removeCommentAction
} from "../smart/comment/saga";
import {commentSlice} from "../smart/comment/slice";

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

    if (/^\/books\/\d+\/comments\/\d+$/.test(url)) {
        yield put(openCommentAction());
    } else if ( /^\/books\/\d+\/comments\/add$/.test(url)) {
        yield put(openEmptyCommentAction());
    } else if (/^\/books\/\d+\/comments\/(\d+)\/delete$/.test(url)) {
        const result = url.match(/^\/books\/\d+\/comments\/(\d+)\/delete$/);
        const id = result[1];
        yield put(commentSlice.actions.deleteElement(id));
        yield put(removeCommentAction());
    } else

    if (url == "/genres") {
        yield put(openGenreListAction());
    } else if (/^\/genres\/\d+$/.test(url)) {
        yield put(openGenreAction());
    } else if (url == "/genres/add") {
        yield put(openEmptyGenreAction());
    } else if (/^\/genres\/(\d+)\/delete$/.test(url)) {
        const result = url.match(/^\/genres\/(\d+)\/delete$/);
        const id = result[1];
        yield put(genreSlice.actions.deleteElement(id));
        yield put(removeGenreAction());
    } else

    if (url == "/authors") {
        yield put(openAuthorListAction());
    } else if (/^\/authors\/\d+$/.test(url)) {
        yield put(openAuthorAction());
    } else if (url == "/authors/add") {
        yield put(openEmptyAuthorAction());
    } else if (/^\/authors\/(\d+)\/delete$/.test(url)) {
        const result = url.match(/^\/authors\/(\d+)\/delete$/);
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
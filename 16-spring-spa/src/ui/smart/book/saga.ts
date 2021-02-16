import { call, put, takeEvery } from "redux-saga/effects";
import {sagaActionTypes} from "store/sagaActionTypes";
import {bookSlice} from "smart/book/slice";

export const displayBookListAction = () => {
    return {
        type: sagaActionTypes.BOOK_LIST_DISPLAY,
    };
};

export function* workerDisplayList() {
    const response = yield call(fetch, "api/books");
    const books = yield call([response, 'json']);
    yield put(bookSlice.actions.list(books));
}

export function* watchDisplayList() {
    yield takeEvery(sagaActionTypes.BOOK_LIST_DISPLAY, workerDisplayList);
}
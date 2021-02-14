import { call, put, takeEvery, select } from "redux-saga/effects";
import {sagaActionTypes} from "store/sagaActionTypes";
import {bookSlice} from "smart/book/slice";

export const displayBookListAction = () => {
    return {
        type: sagaActionTypes.DISPLAY_BOOK_LIST,
    };
};

export function* workerDisplayList() {
    const response = yield call(fetch, "api/books");
    const books = yield call([response, 'json']);
    yield put(bookSlice.actions.list(books));
}

export function* watchDisplayList() {
    yield takeEvery(sagaActionTypes.DISPLAY_BOOK_LIST, workerDisplayList);
}
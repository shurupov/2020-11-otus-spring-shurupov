import { call, put, takeEvery, select } from "redux-saga/effects";
import {sagaActionTypes} from "store/sagaActionTypes";
import {bookSlice} from "smart/book/slice";

export const displayBookListAction = () => {
    return {
        type: sagaActionTypes.BOOK_LIST,
    };
};

export function* workerDisplayList() {
    const response = yield call(fetch, "api/books");
    const books = yield call([response, 'json']);
    console.log("workerDisplayList", books);
    yield put(bookSlice.actions.list(books));
}

export function* watchDisplayList() {
    yield takeEvery(sagaActionTypes.BOOK_LIST, workerDisplayList);
}
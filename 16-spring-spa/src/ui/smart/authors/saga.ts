import {call, put, takeEvery} from "redux-saga/effects";
import {sagaActionTypes} from "store/sagaActionTypes";
import {authorSlice} from "smart/authors/slice";

export const displayAuthorListAction = () => {
    return {
        type: sagaActionTypes.AUTHOR_LIST_DISPLAY,
    };
};

export function* workerDisplayList() {
    const response = yield call(fetch, "/api/authors");
    const authors = yield call([response, 'json']);
    yield put(authorSlice.actions.list(authors));
}

export function* watchDisplayAuthorList() {
    yield takeEvery(sagaActionTypes.AUTHOR_LIST_DISPLAY, workerDisplayList);
}
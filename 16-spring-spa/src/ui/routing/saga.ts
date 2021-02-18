import {put, takeEvery, select} from "redux-saga/effects";
import {sagaActionTypes} from "store/sagaActionTypes";
import {displayBookListAction, getBookAction} from "smart/book/saga";

export const pathSelector = (state: any) => state.router.location.pathname;

export function* workerLocationChange() {
    const url = yield select(pathSelector);
    if (url == "/books") {
        yield put(displayBookListAction());
    } else if (/^\/books\/\d+$/.test(url)) {
        const result = url.match(/^\/books\/(\d+)$/);
        console.log("go to book id", result[1]);
        yield put(getBookAction());
    } else if (/^\/books\/(\d+)\/delete$/.test(url)) {
        const result = url.match(/^\/books\/(\d+)\/delete$/);
        console.log("remove book id", result[1]);
    }
}

export function* watchLocationChange() {
    yield takeEvery(sagaActionTypes.LOCATION_CHANGE, workerLocationChange);
}
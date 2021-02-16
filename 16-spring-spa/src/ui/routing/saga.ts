import {put, takeEvery, select} from "redux-saga/effects";
import {sagaActionTypes} from "store/sagaActionTypes";
import {displayBookListAction} from "smart/book/saga";

export const pathSelector = (state: any) => state.router.location.pathname;

export function* workerLocationChange() {
    const url = yield select(pathSelector);
    if (url == "/books") {
        yield put(displayBookListAction());
    }
}

export function* watchLocationChange() {
    yield takeEvery(sagaActionTypes.LOCATION_CHANGE, workerLocationChange);
}
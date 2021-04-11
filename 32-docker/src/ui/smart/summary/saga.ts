import {sagaActionTypes} from "store/sagaActionTypes";
import {call, put, takeEvery} from "redux-saga/effects";
import {crumbsSlice} from "smart/breadCrumbs/slice";
import {summarySlice} from "smart/summary/slice";
import {fetchOrLogin} from "smart/login/saga";

export const summaryAction = () => {
    return {
        type: sagaActionTypes.SUMMARY_DISPLAY,
    };
};

export function* workerSummary() {
    yield put(crumbsSlice.actions.setCrumbs([
        {caption: "Home", url: "/"},
    ]));
    const counts = yield call(fetchOrLogin, "/api/summary");
    if (!counts) {
        return;
    }
    yield put(summarySlice.actions.setSummary(counts));
}

export function* watchSummary() {
    yield takeEvery(sagaActionTypes.SUMMARY_DISPLAY, workerSummary);
}
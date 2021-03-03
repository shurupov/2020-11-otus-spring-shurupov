import {sagaActionTypes} from "store/sagaActionTypes";
import {call, put, select, takeEvery} from "redux-saga/effects";
import {crumbsSlice} from "smart/breadCrumbs/slice";
import {summarySlice} from "smart/summary/slice";
import {apiPrefixSelector} from "smart/apiSelect/saga";

export const summaryAction = () => {
    return {
        type: sagaActionTypes.SUMMARY_DISPLAY,
    };
};

export function* workerSummary() {
    yield put(crumbsSlice.actions.setCrumbs([
        {caption: "Home", url: "/"},
    ]));
    const apiPrefix = yield select(apiPrefixSelector);
    const response = yield call(fetch, apiPrefix + "/summary");
    const counts = yield call([response, 'json']);
    yield put(summarySlice.actions.setSummary(counts));
}

export function* watchSummary() {
    yield takeEvery(sagaActionTypes.SUMMARY_DISPLAY, workerSummary);
}
import {call, put, takeEvery} from "redux-saga/effects";
import {sagaActionTypes} from "../../store/sagaActionTypes";
import {purchasesListSlice} from "./slice";
import {authenticatedFetch} from "../../utils/auth";
import {processException} from "../../utils/processException";

export const purchasesListAction = () => {
    return {
        type: sagaActionTypes.PURCHASE_LIST,
    };
};

export function* workerPurchasesList(): any {
    try {
        const purchases = yield call(authenticatedFetch, "/api/purchases");
        yield put(purchasesListSlice.actions.list(purchases._embedded.purchases));
    } catch (e) {
        processException(e);
    }
}

export function* watchPurchasesList() {
    yield takeEvery(sagaActionTypes.PURCHASE_LIST, workerPurchasesList);
}
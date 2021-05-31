import {sagaActionTypes} from "../../store/sagaActionTypes";
import {processException} from "../../utils/processException";
import {authenticatedFetch} from "../../utils/auth";
import {call, put, takeEvery} from "redux-saga/effects";
import {AnyAction} from "redux";
import {purchaseSlice} from "./slice";

export const purchaseDisplayAction = (id: number | string) => {
    return {
        type: sagaActionTypes.PURCHASE_DISPLAY,
        payload: id
    };
};

export function* workerPurchaseDisplay(action:AnyAction): any {
    try {
        const purchase = yield call(authenticatedFetch, `/api/purchases/${action.payload}`);
        yield put(purchaseSlice.actions.display(purchase));
    } catch (e) {
        processException(e);
    }
}

export function* watchPurchaseDisplay() {
    yield takeEvery(sagaActionTypes.PURCHASE_DISPLAY, workerPurchaseDisplay);
}
import {sagaActionTypes} from "../../store/sagaActionTypes";
import {processException} from "../../utils/processException";
import {authenticatedFetch} from "../../utils/auth";
import {call, put, takeEvery} from "redux-saga/effects";
import {AnyAction} from "redux";
import {productSlice} from "./slice";

export const productDisplayAction = (purchaseId: number | string, productId: number | string) => {
    return {
        type: sagaActionTypes.PRODUCT_DISPLAY,
        payload: {
            purchaseId,
            productId
        }
    };
};

export function* workerProductDisplay(action:AnyAction): any {
    try {
        const product = yield call(authenticatedFetch, `/api/purchases/${action.payload.purchaseId}/products/${action.payload.productId}`);
        yield put(productSlice.actions.display(product));
    } catch (e) {
        processException(e);
    }
}

export function* watchProductDisplay() {
    yield takeEvery(sagaActionTypes.PRODUCT_DISPLAY, workerProductDisplay);
}
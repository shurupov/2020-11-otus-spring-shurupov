import {call, put, takeEvery} from "redux-saga/effects";
import {sagaActionTypes} from "../../store/sagaActionTypes";
import {authenticatedFetch} from "../../utils/auth";
import {processException} from "../../utils/processException";
import {productsListSlice} from "./slice";
import {AnyAction} from "redux";

export const productsListAction = (purchaseId: number | string) => {
    return {
        type: sagaActionTypes.PRODUCT_LIST,
        payload: purchaseId
    };
};

export function* workerProductsList(action:AnyAction): any {
    try {
        const response = yield call(authenticatedFetch, `/api/purchases/${action.payload}/products`);
        yield put(productsListSlice.actions.list(response._embedded.products));
    } catch (e) {
        processException(e);
    }
}

export function* watchProductsList() {
    yield takeEvery(sagaActionTypes.PRODUCT_LIST, workerProductsList);
}
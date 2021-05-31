import {put, takeEvery, select} from "redux-saga/effects";
import {sagaActionTypes} from "../../store/sagaActionTypes";
import {purchasesListAction} from "../purchasesList/saga";
import {logout} from "../../utils/auth";
import {purchaseDisplayAction} from "../purchase/saga";
import {productsListAction} from "../productList/saga";
import {productDisplayAction} from "../product/saga";

export const pathSelector = (state: any) => state.router.location.pathname;
export const purchaseIdSelector = (state: any) => state.purchase.id;

export function* workerLocationChange(): any {
    const url = yield select(pathSelector);
    if (url == "/purchases") {
        yield put(purchasesListAction());
    } else if (/^\/purchases\/\d+$/.test(url)) {
        const result = url.match(/^\/purchases\/(\d+)$/);
        const purchaseId = result[1];
        yield put(purchaseDisplayAction(purchaseId));
        yield put(productsListAction(purchaseId));
    } else if (/^\/purchases\/\d+\/products\/\d+$/.test(url)) {
        const result = url.match(/^\/purchases\/(\d+)\/products\/(\d+)$/);
        const storedPurchaseId = yield select(purchaseIdSelector);
        const purchaseId = result[1];
        const productId = result[2];
        if (purchaseId != storedPurchaseId) {
            yield put(purchaseDisplayAction(purchaseId));
        }
        yield put(productDisplayAction(purchaseId, productId));
    } else if (url == "/logout") {
        console.log("/logout")
        logout();
    }
}

export function* watchLocationChange() {
    yield takeEvery(sagaActionTypes.LOCATION_CHANGE, workerLocationChange);
}


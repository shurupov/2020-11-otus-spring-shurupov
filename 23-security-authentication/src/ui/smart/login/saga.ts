import {sagaActionTypes} from "store/sagaActionTypes";
import {LoginState} from "components/login/Login";
import {call, put, takeEvery} from "redux-saga/effects";
import {goBack, push} from "connected-react-router";

export const loginAction = (payload: LoginState) => {
    return {
        type: sagaActionTypes.AUTHENTICATION_LOGIN,
        payload,
    };
};

export function* workerLogin(action: any) {
    const response = yield call(fetch, "/api/login", {
        method: "POST",
        body: new URLSearchParams(action.payload)
    });
    const json = yield call([response, 'json']);
    if (json.status == "success") {
        yield put(goBack());
    }
}

export function* watchLogin() {
    yield takeEvery(sagaActionTypes.AUTHENTICATION_LOGIN, workerLogin);
}

export function *fetchOrLogin(url: string, method = "GET", body = false) {
    const response = yield call(fetch, url, {
        method,
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: body ? JSON.stringify(body) : undefined
    });
    const json = yield call([response, 'json']);
    if (json.hasOwnProperty("status") && json.status == "error") {
        yield put(push("/auth"));
        return false;
    }
    return json;
}
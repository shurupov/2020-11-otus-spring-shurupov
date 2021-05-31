import {AuthRequest} from "./Authetication";
import {sagaActionTypes} from "../../store/sagaActionTypes";
import {call, takeEvery} from "redux-saga/effects";
import {loginFetch} from "../../utils/auth";
import {AnyAction} from "redux";
import {history} from "../../store/store";

export const loginAction = (authRequest: AuthRequest) => {
    return {
        type: sagaActionTypes.AUTHENTICATION_LOGIN,
        payload: authRequest
    }
}

export function* workerLogin(action:AnyAction): any {
    try {
        const jwtResponse = yield call(loginFetch, action.payload);
        localStorage.setItem("jwttoken", jwtResponse.jwttoken);
        history.push("/");
    } catch (e) {
        if (e.name == "BadResponse" && e.response.status == 401) {
            console.log("Ошибка аутентификации");
        }
    }
}

export function* watchLogin() {
    yield takeEvery(sagaActionTypes.AUTHENTICATION_LOGIN, workerLogin);
}
import {connect} from "react-redux";
import Login, {LoginState} from "../../components/login/Login";
import {Dispatch} from "redux";
import {loginAction} from "./saga";

const mapDispatchToProps = (dispatch: Dispatch) => {
    return {
        login: (payload: LoginState) => {
            dispatch(loginAction(payload));
        }
    }
}

export const ConnectedLogin = connect(null, mapDispatchToProps)(Login);
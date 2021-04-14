import {connect} from "react-redux";
import {LoginLogoutButton} from "../../components/common/LoginLogoutButton";
import {Dispatch} from "redux";
import {logoutAction} from "./saga";

const mapStateToProps = (storeState: any) => {
    return {
        logged: storeState.auth.logged,
    }
};

const mapDispatchToProps = (dispatch: Dispatch) => {
    return {
        logout: () => {
            dispatch(logoutAction());
        }
    }
}

export const ConnectedLoginButton = connect(mapStateToProps, mapDispatchToProps)(LoginLogoutButton);
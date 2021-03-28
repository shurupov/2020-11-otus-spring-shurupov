import {connect} from "react-redux";
import Message from "components/message/Message";
import {Dispatch} from "redux";
import {alertSlice} from "smart/message/alertSlice";

const mapStateToProps = (storeState: any) => {
    return {
        text: storeState.alert.text,
        visible: storeState.alert.visible,
    }
};

const mapDispatchToProps = (dispatch: Dispatch) => {
    return {
        close: () => {
            dispatch(alertSlice.actions.hide());
        }
    }
}

export const ConnectedMessage = connect(mapStateToProps, mapDispatchToProps)(Message);
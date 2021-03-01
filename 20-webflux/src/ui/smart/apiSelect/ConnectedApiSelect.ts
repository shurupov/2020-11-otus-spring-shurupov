import ApiSelect from "components/apiSelect/ApiSelect";
import {connect} from "react-redux";
import {Dispatch} from "redux";
import {apiSlice} from "smart/apiSelect/slice";

const mapStateToProps = (storeState: any) => {
    return {
        uriPrefix: storeState.api.prefix,
    }
};

const mapDispatchToProps = (dispatch: Dispatch) => {
    return {
        updatePrefix: (prefix) => {
            dispatch(apiSlice.actions.update(prefix));
        }
    }
};

export const ConnectedApiSelect = connect(mapStateToProps, mapDispatchToProps)(ApiSelect);
import {connect} from "react-redux";
import {LeftMenu} from "./LeftMenu";
import {switchMenuItem} from "../../utils/switchMenuItem";

const mapStateToProps = (storeState: any) => {
    return {
        selected: switchMenuItem(["/profile", "/orders", "/my-purchases"], storeState.router.location.pathname)
    }
};

export const ConnectedLeftMenu = connect(mapStateToProps)(LeftMenu);
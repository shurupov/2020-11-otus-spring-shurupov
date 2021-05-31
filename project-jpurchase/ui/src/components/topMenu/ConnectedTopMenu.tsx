import {connect} from "react-redux";
import {TopMenu} from "./TopMenu";
import {switchMenuItem} from "../../utils/switchMenuItem";

const mapStateToProps = (storeState: any) => {
    return {
        selected: switchMenuItem(["/purchases", "/auth"], storeState.router.location.pathname)
    }
};

export const ConnectedTopMenu = connect(mapStateToProps)(TopMenu);
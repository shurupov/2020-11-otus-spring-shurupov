import {connect} from "react-redux";
import Crumbs from "../../components/common/Crumbs";

const mapStateToProps = (storeState: any) => {
    return {
        items: storeState.crumbs.items,
    }
};

export const ConnectedCrumbs = connect(mapStateToProps)(Crumbs);
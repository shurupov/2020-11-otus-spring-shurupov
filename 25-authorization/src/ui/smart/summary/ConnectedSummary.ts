import {connect} from "react-redux";
import Summary from "components/Summary";

const mapStateToProps = (storeState: any) => {
    return {
        counts: storeState.summary.counts
    }
};

export const ConnectedSummary = connect(mapStateToProps)(Summary);
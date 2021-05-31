import {PurchasesList} from "./PurchasesList";
import {connect} from "react-redux";

const mapStateToProps = (storeState: any) => {
    return {
        purchasesList: storeState.purchasesList.list
    }
};

export const ConnectedPurchasesList = connect(mapStateToProps)(PurchasesList);
import {Purchase, PurchaseProps} from "./Purchase";
import {connect} from "react-redux";

const mapStateToProps = (storeState: any) => {
    return {
        ...storeState.purchase
    } as PurchaseProps;
};

export const ConnectedPurchase = connect(mapStateToProps)(Purchase);
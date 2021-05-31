import {connect} from "react-redux";
import {Breadcrumbs} from "./BreadCrumbs";

const mapStateToProps = (storeState: any) => {
    return {
        purchaseName: storeState.purchase.name,
        purchaseId: storeState.purchase.id,
        productName: storeState.product.name,
        productId: storeState.product.id,
    }
};

export const ConnectedBreadCrumbs = connect(mapStateToProps)(Breadcrumbs);
import {connect} from "react-redux";
import {ProductList} from "./ProductList";

const mapStateToProps = (storeState: any) => {
    return {
        purchaseId: storeState.purchase.id,
        products: storeState.products
    }
};

export const ConnectedProductList = connect(mapStateToProps)(ProductList);
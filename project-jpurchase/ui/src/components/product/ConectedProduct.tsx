import {connect} from "react-redux";
import {Product, ProductProps} from "./Product";

const mapStateToProps = (storeState: any) => {
    return {
        ...storeState.product
    } as ProductProps;
};

export const ConnectedProduct = connect(mapStateToProps)(Product);
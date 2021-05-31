import {combineReducers} from "redux";
import {connectRouter} from "connected-react-router";
import {purchasesListSlice} from "../components/purchasesList/slice";
import {purchaseSlice} from "../components/purchase/slice";
import {productsListSlice} from "../components/productList/slice";
import {productSlice} from "../components/product/slice";

export const createRootReducer = (history: any) => combineReducers({
    router: connectRouter(history),
    purchasesList: purchasesListSlice.reducer,
    purchase: purchaseSlice.reducer,
    products: productsListSlice.reducer,
    product: productSlice.reducer,
});
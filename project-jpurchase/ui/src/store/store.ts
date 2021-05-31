import {createBrowserHistory} from "history";
import createSagaMiddleware from "redux-saga";
import {applyMiddleware, compose, createStore, PreloadedState, Store} from "redux";
import {composeWithDevTools} from "redux-devtools-extension";
import {createRootReducer} from "./reducer";
import { routerMiddleware } from "connected-react-router";
import {watchLocationChange} from "../components/routing/saga";
import {watchPurchasesList} from "../components/purchasesList/saga";
import {watchLogin} from "../components/authentication/saga";
import {watchPurchaseDisplay} from "../components/purchase/saga";
import {watchProductsList} from "../components/productList/saga";
import {watchProductDisplay} from "../components/product/saga";

export const history = createBrowserHistory();

const sagaMiddleware = createSagaMiddleware();

export default function configureStore(preloadedState: PreloadedState<any>) {
    const store = createStore(
        createRootReducer(history), // root reducer with router state
        preloadedState,
        compose(
            composeWithDevTools(
                applyMiddleware(
                    routerMiddleware(history),
                    sagaMiddleware
                )
            ),
        ),
    )

    return store;
}

export const store: Store = configureStore({});

sagaMiddleware.run(watchLocationChange);
sagaMiddleware.run(watchProductsList);
sagaMiddleware.run(watchProductDisplay);
sagaMiddleware.run(watchPurchasesList);
sagaMiddleware.run(watchPurchaseDisplay);
sagaMiddleware.run(watchLogin);
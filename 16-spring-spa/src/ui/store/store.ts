import {applyMiddleware, compose, createStore, PreloadedState, Store} from "redux";
import {composeWithDevTools} from "redux-devtools-extension";
import createSagaMiddleware from "redux-saga";
import {createRootReducer} from "./reducer";
import {createBrowserHistory} from "history";
import {routerMiddleware} from "connected-react-router";
import {watchLocationChange} from "../routing/saga";
import {watchDisplayList} from "smart/book/saga";

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
sagaMiddleware.run(watchDisplayList);
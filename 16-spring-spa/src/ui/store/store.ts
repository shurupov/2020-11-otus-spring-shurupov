import {applyMiddleware, createStore, Store} from "redux";
import {composeWithDevTools} from "redux-devtools-extension";
import createSagaMiddleware from "redux-saga";
import {reducer} from "./reducer";
import {displayBookListAction, watchDisplayList} from "smart/book/saga";

const sagaMiddleware = createSagaMiddleware();

export const store: Store = createStore(
    reducer,
    composeWithDevTools(applyMiddleware(sagaMiddleware))
);

sagaMiddleware.run(watchDisplayList);
store.dispatch(displayBookListAction());
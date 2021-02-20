import {applyMiddleware, compose, createStore, PreloadedState, Store} from "redux";
import {composeWithDevTools} from "redux-devtools-extension";
import createSagaMiddleware from "redux-saga";
import {createRootReducer} from "./reducer";
import {createBrowserHistory} from "history";
import {routerMiddleware} from "connected-react-router";
import {watchLocationChange} from "../routing/saga";
import {
    watchAddBook,
    watchDisplayBooksList,
    watchOpenBook,
    watchOpenEmptyBook,
    watchRemoveBook,
    watchUpdateBook
} from "smart/book/saga";
import {watchDisplayAuthorList} from "smart/authors/saga";
import {watchDisplayGenreList} from "smart/genres/saga";
import {watchSummary} from "smart/summary/saga";

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

sagaMiddleware.run(watchSummary);

sagaMiddleware.run(watchDisplayBooksList);
sagaMiddleware.run(watchOpenBook);
sagaMiddleware.run(watchOpenEmptyBook);
sagaMiddleware.run(watchDisplayAuthorList);
sagaMiddleware.run(watchDisplayGenreList);
sagaMiddleware.run(watchUpdateBook);
sagaMiddleware.run(watchAddBook);
sagaMiddleware.run(watchRemoveBook);
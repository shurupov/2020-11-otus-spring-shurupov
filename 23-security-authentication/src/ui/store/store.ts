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
import {
    watchAddAuthor,
    watchDisplayAuthorList, watchOpenAuthor,
    watchOpenEmptyAuthor,
    watchRemoveAuthor,
    watchUpdateAuthor
} from "smart/authors/saga";
import {
    watchAddGenre,
    watchDisplayGenreList,
    watchOpenEmptyGenre,
    watchOpenGenre, watchRemoveGenre,
    watchUpdateGenre
} from "smart/genre/saga";
import {watchSummary} from "smart/summary/saga";
import {
    watchAddComment,
    watchDisplayCommentList,
    watchOpenComment,
    watchOpenEmptyComment, watchRemoveComment,
    watchUpdateComment
} from "smart/comment/saga";
import {watchLogin, watchLogout} from "smart/login/saga";

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
sagaMiddleware.run(watchUpdateBook);
sagaMiddleware.run(watchAddBook);
sagaMiddleware.run(watchRemoveBook);

sagaMiddleware.run(watchDisplayCommentList);
sagaMiddleware.run(watchOpenComment);
sagaMiddleware.run(watchOpenEmptyComment);
sagaMiddleware.run(watchUpdateComment);
sagaMiddleware.run(watchAddComment);
sagaMiddleware.run(watchRemoveComment);

sagaMiddleware.run(watchDisplayGenreList);
sagaMiddleware.run(watchOpenGenre);
sagaMiddleware.run(watchOpenEmptyGenre);
sagaMiddleware.run(watchUpdateGenre);
sagaMiddleware.run(watchAddGenre);
sagaMiddleware.run(watchRemoveGenre);

sagaMiddleware.run(watchDisplayAuthorList);
sagaMiddleware.run(watchOpenAuthor);
sagaMiddleware.run(watchOpenEmptyAuthor);
sagaMiddleware.run(watchUpdateAuthor);
sagaMiddleware.run(watchAddAuthor);
sagaMiddleware.run(watchRemoveAuthor);

sagaMiddleware.run(watchLogin);
sagaMiddleware.run(watchLogout);
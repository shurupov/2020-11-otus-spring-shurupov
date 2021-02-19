import {call, put, select, takeEvery} from "redux-saga/effects";
import {sagaActionTypes} from "store/sagaActionTypes";
import {bookSlice} from "smart/book/slice";
import {displayAuthorListAction} from "smart/authors/saga";
import {displayGenreListAction} from "smart/genres/saga";
import {push} from "connected-react-router";

export const displayBookListAction = () => {
    return {
        type: sagaActionTypes.BOOK_LIST_DISPLAY,
    };
};

export const getBookAction = () => {
    return {
        type: sagaActionTypes.BOOK_ELEMENT_DISPLAY,
    };
};

export const updateBookAction = () => {
    return {
        type: sagaActionTypes.BOOK_ELEMENT_UPDATE,
    };
};

export const removeBookAction = () => {
    return {
        type: sagaActionTypes.BOOK_ELEMENT_REMOVE,
    };
};

export function* workerDisplayList() {
    const response = yield call(fetch, "/api/books");
    const books = yield call([response, 'json']);
    yield put(bookSlice.actions.list(books));
}

const bookIdSelector = (state: any) => {
    const url = state.router.location.pathname;
    const result = url.match(/^\/books\/(\d+)$/);
    return parseFloat(result[1]);
};

const bookSelector = (state: any) => state.book.element;
const bookToDeleteIdSelector = (state: any) => state.book.elementToDeleteId;

export function* workerGetBook() {
    yield put(displayAuthorListAction());
    yield put(displayGenreListAction());
    const id = yield select(bookIdSelector);
    const response = yield call(fetch, "/api/books/" + id);
    const book = yield call([response, 'json']);
    yield put(bookSlice.actions.updateElementView(book));
}

export function* watchDisplayBooksList() {
    yield takeEvery(sagaActionTypes.BOOK_LIST_DISPLAY, workerDisplayList);
}

export function* watchGetBook() {
    yield takeEvery(sagaActionTypes.BOOK_ELEMENT_DISPLAY, workerGetBook);
}

export function* workerUpdateBook() {
    const book = yield select(bookSelector);
    yield call(fetch, "/api/books/" + book.id, {
        method: "PUT",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify(book)
    });
    yield put(push("/books"));
}

export function* watchUpdateBook() {
    yield takeEvery(sagaActionTypes.BOOK_ELEMENT_UPDATE, workerUpdateBook);
}

export function* workerRemoveBook() {
    const id = yield select(bookToDeleteIdSelector);
    yield call(fetch, "/api/books/" + id, {
        method: "DELETE",
    });
    yield put(push("/books"));
}

export function* watchRemoveBook() {
    yield takeEvery(sagaActionTypes.BOOK_ELEMENT_REMOVE, workerRemoveBook);
}
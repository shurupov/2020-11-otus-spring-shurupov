import {call, put, select, takeEvery} from "redux-saga/effects";
import {sagaActionTypes} from "store/sagaActionTypes";
import {bookSlice} from "smart/book/slice";
import {displayAuthorListAction} from "smart/authors/saga";
import {displayGenreListAction} from "smart/genres/saga";
import {push} from "connected-react-router";
import {EditorType} from "components/book/BookEditView";

export const openBookListAction = () => {
    return {
        type: sagaActionTypes.BOOK_LIST_DISPLAY,
    };
};

export const openBookAction = () => {
    return {
        type: sagaActionTypes.BOOK_ELEMENT_OPEN,
    };
};

export const openEmptyBookAction = () => {
    return {
        type: sagaActionTypes.BOOK_ELEMENT_OPEN_EMPTY,
    };
};

export const updateBookAction = () => {
    return {
        type: sagaActionTypes.BOOK_ELEMENT_UPDATE,
    };
};

export const addBookAction = () => {
    return {
        type: sagaActionTypes.BOOK_ELEMENT_ADD,
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

export function* workerOpenBook() {
    yield put(displayAuthorListAction());
    yield put(displayGenreListAction());
    const id = yield select(bookIdSelector);
    const response = yield call(fetch, "/api/books/" + id);
    const book = yield call([response, 'json']);
    yield put(bookSlice.actions.updateElementView(book));
    yield put(bookSlice.actions.switchEditor(EditorType.EDIT));
}

export function* workerOpenEmptyBook() {
    yield put(displayAuthorListAction());
    yield put(displayGenreListAction());
    yield put(bookSlice.actions.updateElementView({
        id: null,
        name: "",
        authorId: 0,
        genres: []
    }));
    yield put(bookSlice.actions.switchEditor(EditorType.ADD));
}

export function* watchDisplayBooksList() {
    yield takeEvery(sagaActionTypes.BOOK_LIST_DISPLAY, workerDisplayList);
}

export function* watchOpenBook() {
    yield takeEvery(sagaActionTypes.BOOK_ELEMENT_OPEN, workerOpenBook);
}

export function* watchOpenEmptyBook() {
    yield takeEvery(sagaActionTypes.BOOK_ELEMENT_OPEN_EMPTY, workerOpenEmptyBook);
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

export function* workerAddBook() {
    const book = yield select(bookSelector);
    yield call(fetch, "/api/books", {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify(book)
    });
    yield put(push("/books"));
}

export function* watchAddBook() {
    yield takeEvery(sagaActionTypes.BOOK_ELEMENT_ADD, workerAddBook);
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
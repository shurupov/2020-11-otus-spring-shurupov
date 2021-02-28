import {call, put, select, takeEvery} from "redux-saga/effects";
import {sagaActionTypes} from "store/sagaActionTypes";
import {bookSlice} from "smart/book/slice";
import {openAuthorListAction} from "smart/authors/saga";
import {push} from "connected-react-router";
import {crumbsSlice} from "smart/breadCrumbs/slice";
import {EditorType} from "../../utils/EditorType";

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
    yield put(crumbsSlice.actions.setCrumbs([
        {caption: "Home", url: "/"},
        {caption: "Books", url: "/books"},
    ]));
    const response = yield call(fetch, "/api/v1/books");
    const books = yield call([response, 'json']);
    yield put(bookSlice.actions.list(books));
}

const bookIdSelector = (state: any) => {
    const url = state.router.location.pathname;
    const result = url.match(/^\/books\/([\dabcdef]+)$/);
    return result[1];
};

export const bookSelector = (state: any) => state.book.element;
const bookToDeleteIdSelector = (state: any) => state.book.elementToDeleteId;

export function* workerOpenBook() {
    yield put(openAuthorListAction());
    //yield put(openGenreListAction());
    const id = yield select(bookIdSelector);
    const response = yield call(fetch, "/api/v1/books/" + id);
    const book = yield call([response, 'json']);
    yield put(crumbsSlice.actions.setCrumbs([
        {caption: "Home", url: "/"},
        {caption: "Books", url: "/books"},
        {caption: book.name, url: ""},
    ]));
    yield put(bookSlice.actions.openElement(book));
    yield put(bookSlice.actions.switchEditor(EditorType.EDIT));
}

export function* workerOpenEmptyBook() {
    yield put(openAuthorListAction());
    //yield put(openGenreListAction());
    yield put(bookSlice.actions.openElement({
        id: null,
        name: "",
        authorId: 0,
        genres: []
    }));
    yield put(crumbsSlice.actions.setCrumbs([
        {caption: "Home", url: "/"},
        {caption: "Books", url: "/books"},
        {caption: "New Book", url: ""},
    ]));
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
    yield call(fetch, "/api/v1/books/" + book.id, {
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
    yield call(fetch, "/api/v1/books", {
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
    yield call(fetch, "/api/v1/books/" + id, {
        method: "DELETE",
    });
    yield put(push("/books"));
}

export function* watchRemoveBook() {
    yield takeEvery(sagaActionTypes.BOOK_ELEMENT_REMOVE, workerRemoveBook);
}
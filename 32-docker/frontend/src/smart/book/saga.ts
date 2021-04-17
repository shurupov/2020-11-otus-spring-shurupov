import {call, put, select, takeEvery} from "redux-saga/effects";
import {sagaActionTypes} from "../../store/sagaActionTypes";
import {bookSlice} from "./slice";
import {push} from "connected-react-router";
import {crumbsSlice} from "../breadCrumbs/slice";
import {EditorType} from "../../utils/EditorType";
import {fetchOrLogin, justFetch} from "../login/saga";
import {authorSlice} from "../authors/slice";
import {genreSlice} from "../genre/slice";
import {commentSlice} from "../comment/slice";

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
    const books = yield call(fetchOrLogin, "/api/books");
    if (!books) {
        return;
    }
    yield put(bookSlice.actions.list(books));
}

const bookIdSelector = (state: any) => {
    const url = state.router.location.pathname;
    const result = url.match(/^\/books\/(\d+)$/);
    return parseFloat(result[1]);
};

export const bookSelector = (state: any) => state.book.element;
const bookToDeleteIdSelector = (state: any) => state.book.elementToDeleteId;

export function* workerOpenBook() {
    const id = yield select(bookIdSelector);
    const book = yield call(fetchOrLogin, "/api/books/" + id);
    if (!book) {
        return;
    }
    const authors = yield call(fetchOrLogin, "/api/authors");
    yield put(authorSlice.actions.list(authors));
    const genres = yield call(fetchOrLogin, "/api/genres");
    yield put(genreSlice.actions.list(genres));
    const comments = yield call(fetchOrLogin, "/api/books/" + book.id + "/comments");
    yield put(commentSlice.actions.list(comments));
    yield put(crumbsSlice.actions.setCrumbs([
        {caption: "Home", url: "/"},
        {caption: "Books", url: "/books"},
        {caption: book.name, url: ""},
    ]));
    yield put(bookSlice.actions.openElement(book));
    yield put(bookSlice.actions.switchEditor(EditorType.EDIT));
}

export function* workerOpenEmptyBook() {
    const authors = yield call(fetchOrLogin, "/api/authors");
    if (!authors) {
        return;
    }
    yield put(authorSlice.actions.list(authors));
    const genres = yield call(fetchOrLogin, "/api/genres");
    yield put(genreSlice.actions.list(genres));
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
    const response = yield call(fetchOrLogin, "/api/books/" + book.id, "PUT", book);
    if (!response) {
        return;
    }
    yield put(push("/books"));
}

export function* watchUpdateBook() {
    yield takeEvery(sagaActionTypes.BOOK_ELEMENT_UPDATE, workerUpdateBook);
}

export function* workerAddBook() {
    const book = yield select(bookSelector);
    const response = yield call(fetchOrLogin, "/api/books", "POST", book);
    if (!response) {
        return;
    }
    yield put(push("/books"));
}

export function* watchAddBook() {
    yield takeEvery(sagaActionTypes.BOOK_ELEMENT_ADD, workerAddBook);
}

export function* workerRemoveBook() {
    const id = yield select(bookToDeleteIdSelector);
    yield call(justFetch, "/api/books/" + id, "DELETE");
    yield put(push("/books"));
}

export function* watchRemoveBook() {
    yield takeEvery(sagaActionTypes.BOOK_ELEMENT_REMOVE, workerRemoveBook);
}
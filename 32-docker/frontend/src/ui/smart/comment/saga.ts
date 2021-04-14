import {call, put, select, takeEvery} from "redux-saga/effects";
import {sagaActionTypes} from "store/sagaActionTypes";
import {commentSlice} from "smart/comment/slice";
import {crumbsSlice} from "smart/breadCrumbs/slice";
import {EditorType} from "../../utils/EditorType";
import {push} from "connected-react-router";
import {bookSelector} from "smart/book/saga";
import {fetchOrLogin} from "smart/login/saga";

export const openCommentListAction = () => {
    return {
        type: sagaActionTypes.COMMENT_LIST_DISPLAY,
    };
};

export const openCommentAction = () => {
    return {
        type: sagaActionTypes.COMMENT_ELEMENT_OPEN,
    };
};
export const openEmptyCommentAction = () => {
    return {
        type: sagaActionTypes.COMMENT_ELEMENT_OPEN_EMPTY,
    };
};

export const updateCommentAction = () => {
    return {
        type: sagaActionTypes.COMMENT_ELEMENT_UPDATE,
    };
};

export const addCommentAction = () => {
    return {
        type: sagaActionTypes.COMMENT_ELEMENT_ADD,
    };
};

export const removeCommentAction = () => {
    return {
        type: sagaActionTypes.COMMENT_ELEMENT_REMOVE,
    };
};

export function* workerDisplayList() {
    const book = yield select(bookSelector);
    yield put(crumbsSlice.actions.setCrumbs([
        {caption: "Home", url: "/"},
        {caption: "Books", url: "/books"},
        {caption: book.name, url: "/books/" + book.id},
        {caption: "Comments", url: "/books/" + book.id + "/comments"},
    ]));
    const comments = yield call(fetchOrLogin, "/api/books/" + book.id + "/comments");
    if (!comments) {
        return;
    }
    yield put(commentSlice.actions.list(comments));
}

export function* watchDisplayCommentList() {
    yield takeEvery(sagaActionTypes.COMMENT_LIST_DISPLAY, workerDisplayList);
}

const commentIdSelector = (state: any) => {
    const url = state.router.location.pathname;
    const result = url.match(/^\/books\/\d+\/comments\/(\d+)$/);
    return parseFloat(result[1]);
};

export function* workerOpenComment() {
    const book = yield select(bookSelector);
    const id = yield select(commentIdSelector);
    const comment = yield call(fetchOrLogin, "/api/books/" + book.id + "/comments/" + id);
    if (!comment) {
        return;
    }
    yield put(crumbsSlice.actions.setCrumbs([
        {caption: "Home", url: "/"},
        {caption: "Books", url: "/books"},
        {caption: book.name, url: "/books/" + book.id},
        {caption: comment.text, url: ""},
    ]));
    yield put(commentSlice.actions.openElement(comment));
    yield put(commentSlice.actions.switchEditor(EditorType.EDIT));
}

export function* watchOpenComment() {
    yield takeEvery(sagaActionTypes.COMMENT_ELEMENT_OPEN, workerOpenComment);
}

export function* workerOpenEmptyComment() {
    const book = yield select(bookSelector);
    yield put(commentSlice.actions.openElement({
        id: null,
        name: "",
    }));
    yield put(crumbsSlice.actions.setCrumbs([
        {caption: "Home", url: "/"},
        {caption: "Books", url: "/books"},
        {caption: book.name, url: "/books/" + book.id},
        {caption: "New Comment", url: ""},
    ]));
    yield put(commentSlice.actions.switchEditor(EditorType.ADD));
}

export function* watchOpenEmptyComment() {
    yield takeEvery(sagaActionTypes.COMMENT_ELEMENT_OPEN_EMPTY, workerOpenEmptyComment);
}

const commentSelector = (state: any) => state.comment.element;

export function* workerUpdateComment() {
    const book = yield select(bookSelector);
    const comment = yield select(commentSelector);
    const response = yield call(fetchOrLogin, "/api/books/" + book.id + "/comments/" + comment.id, "PUT", comment);
    if (!response) {
        return;
    }
    yield put(push("/books/" + book.id));
}

export function* watchUpdateComment() {
    yield takeEvery(sagaActionTypes.COMMENT_ELEMENT_UPDATE, workerUpdateComment);
}

export function* workerAddComment() {
    const book = yield select(bookSelector);
    const comment = yield select(commentSelector);
    const response = yield call(fetchOrLogin, "/api/books/" + book.id + "/comments", "POST", comment);
    if (!response) {
        return;
    }
    yield put(push("/books/" + book.id));
}

export function* watchAddComment() {
    yield takeEvery(sagaActionTypes.COMMENT_ELEMENT_ADD, workerAddComment);
}

const commentToDeleteIdSelector = (state: any) => state.comment.elementToDeleteId;

export function* workerRemoveComment() {
    const book = yield select(bookSelector);
    const id = yield select(commentToDeleteIdSelector);
    const response = yield call(fetchOrLogin, "/api/books/" + book.id + "/comments/" + id, "DELETE");
    if (!response) {
        return;
    }
    yield put(push("/books/" + book.id));
}

export function* watchRemoveComment() {
    yield takeEvery(sagaActionTypes.COMMENT_ELEMENT_REMOVE, workerRemoveComment);
}
import {call, put, select, takeEvery} from "redux-saga/effects";
import {sagaActionTypes} from "store/sagaActionTypes";
import {authorSlice} from "smart/authors/slice";
import {crumbsSlice} from "smart/breadCrumbs/slice";
import {EditorType} from "../../utils/EditorType";
import {push} from "connected-react-router";

export const openAuthorListAction = () => {
    return {
        type: sagaActionTypes.AUTHOR_LIST_DISPLAY,
    };
};

export const openAuthorAction = () => {
    return {
        type: sagaActionTypes.AUTHOR_ELEMENT_OPEN,
    };
};
export const openEmptyAuthorAction = () => {
    return {
        type: sagaActionTypes.AUTHOR_ELEMENT_OPEN_EMPTY,
    };
};

export const updateAuthorAction = () => {
    return {
        type: sagaActionTypes.AUTHOR_ELEMENT_UPDATE,
    };
};

export const addAuthorAction = () => {
    return {
        type: sagaActionTypes.AUTHOR_ELEMENT_ADD,
    };
};

export const removeAuthorAction = () => {
    return {
        type: sagaActionTypes.AUTHOR_ELEMENT_REMOVE,
    };
};

export function* workerDisplayList() {
    yield put(crumbsSlice.actions.setCrumbs([
        {caption: "Home", url: "/"},
        {caption: "Authors", url: "/authors"},
    ]));
    const response = yield call(fetch, "/api/authors");
    const authors = yield call([response, 'json']);
    yield put(authorSlice.actions.list(authors));
}

export function* watchDisplayAuthorList() {
    yield takeEvery(sagaActionTypes.AUTHOR_LIST_DISPLAY, workerDisplayList);
}

const authorIdSelector = (state: any) => {
    const url = state.router.location.pathname;
    const result = url.match(/^\/authors\/(\d+)$/);
    return parseFloat(result[1]);
};

export function* workerOpenAuthor() {
    const id = yield select(authorIdSelector);
    const response = yield call(fetch, "/api/authors/" + id);
    const author = yield call([response, 'json']);
    yield put(crumbsSlice.actions.setCrumbs([
        {caption: "Home", url: "/"},
        {caption: "Authors", url: "/authors"},
        {caption: author.name, url: ""},
    ]));
    yield put(authorSlice.actions.openElement(author));
    yield put(authorSlice.actions.switchEditor(EditorType.EDIT));
}

export function* watchOpenAuthor() {
    yield takeEvery(sagaActionTypes.AUTHOR_ELEMENT_OPEN, workerOpenAuthor);
}

export function* workerOpenEmptyAuthor() {
    yield put(authorSlice.actions.openElement({
        id: null,
        name: "",
    }));
    yield put(crumbsSlice.actions.setCrumbs([
        {caption: "Home", url: "/"},
        {caption: "Authors", url: "/authors"},
        {caption: "New Author", url: ""},
    ]));
    yield put(authorSlice.actions.switchEditor(EditorType.ADD));
}

export function* watchOpenEmptyAuthor() {
    yield takeEvery(sagaActionTypes.AUTHOR_ELEMENT_OPEN_EMPTY, workerOpenEmptyAuthor);
}

const authorSelector = (state: any) => state.author.element;

export function* workerUpdateAuthor() {
    const author = yield select(authorSelector);
    yield call(fetch, "/api/authors/" + author.id, {
        method: "PUT",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify(author)
    });
    yield put(push("/authors"));
}

export function* watchUpdateAuthor() {
    yield takeEvery(sagaActionTypes.AUTHOR_ELEMENT_UPDATE, workerUpdateAuthor);
}

export function* workerAddAuthor() {
    const author = yield select(authorSelector);
    yield call(fetch, "/api/authors", {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify(author)
    });
    yield put(push("/authors"));
}

export function* watchAddAuthor() {
    yield takeEvery(sagaActionTypes.AUTHOR_ELEMENT_ADD, workerAddAuthor);
}

const authorToDeleteIdSelector = (state: any) => state.author.elementToDeleteId;

export function* workerRemoveAuthor() {
    const id = yield select(authorToDeleteIdSelector);
    yield call(fetch, "/api/authors/" + id, {
        method: "DELETE",
    });
    yield put(push("/authors"));
}

export function* watchRemoveAuthor() {
    yield takeEvery(sagaActionTypes.AUTHOR_ELEMENT_REMOVE, workerRemoveAuthor);
}
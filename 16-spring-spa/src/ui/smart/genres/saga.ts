import {call, put, select, takeEvery} from "redux-saga/effects";
import {sagaActionTypes} from "store/sagaActionTypes";
import {genreSlice} from "smart/genres/slice";
import {crumbsSlice} from "smart/breadCrumbs/slice";
import {EditorType} from "../../utils/EditorType";
import {push} from "connected-react-router";

export const openGenreListAction = () => {
    return {
        type: sagaActionTypes.GENRE_LIST_DISPLAY,
    };
};

export const openGenreAction = () => {
    return {
        type: sagaActionTypes.GENRE_ELEMENT_OPEN,
    };
};
export const openEmptyGenreAction = () => {
    return {
        type: sagaActionTypes.GENRE_ELEMENT_OPEN_EMPTY,
    };
};

export const updateGenreAction = () => {
    return {
        type: sagaActionTypes.GENRE_ELEMENT_UPDATE,
    };
};

export const addGenreAction = () => {
    return {
        type: sagaActionTypes.GENRE_ELEMENT_ADD,
    };
};

export const removeGenreAction = () => {
    return {
        type: sagaActionTypes.GENRE_ELEMENT_REMOVE,
    };
};

export function* workerDisplayList() {
    yield put(crumbsSlice.actions.setCrumbs([
        {caption: "Home", url: "/"},
        {caption: "Genres", url: "/genres"},
    ]));
    const response = yield call(fetch, "/api/genres");
    const genres = yield call([response, 'json']);
    yield put(genreSlice.actions.list(genres));
}

export function* watchDisplayGenreList() {
    yield takeEvery(sagaActionTypes.GENRE_LIST_DISPLAY, workerDisplayList);
}

const genreIdSelector = (state: any) => {
    const url = state.router.location.pathname;
    const result = url.match(/^\/genres\/(\d+)$/);
    return parseFloat(result[1]);
};

export function* workerOpenGenre() {
    const id = yield select(genreIdSelector);
    const response = yield call(fetch, "/api/genres/" + id);
    const genre = yield call([response, 'json']);
    yield put(crumbsSlice.actions.setCrumbs([
        {caption: "Home", url: "/"},
        {caption: "Genres", url: "/genres"},
        {caption: genre.name, url: ""},
    ]));
    yield put(genreSlice.actions.openElement(genre));
    yield put(genreSlice.actions.switchEditor(EditorType.EDIT));
}

export function* watchOpenGenre() {
    yield takeEvery(sagaActionTypes.GENRE_ELEMENT_OPEN, workerOpenGenre);
}

export function* workerOpenEmptyGenre() {
    yield put(genreSlice.actions.openElement({
        id: null,
        name: "",
    }));
    yield put(crumbsSlice.actions.setCrumbs([
        {caption: "Home", url: "/"},
        {caption: "Genres", url: "/genres"},
        {caption: "New Genre", url: ""},
    ]));
    yield put(genreSlice.actions.switchEditor(EditorType.ADD));
}

export function* watchOpenEmptyGenre() {
    yield takeEvery(sagaActionTypes.GENRE_ELEMENT_OPEN_EMPTY, workerOpenEmptyGenre);
}

const genreSelector = (state: any) => state.genre.element;

export function* workerUpdateGenre() {
    const genre = yield select(genreSelector);
    yield call(fetch, "/api/genres/" + genre.id, {
        method: "PUT",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify(genre)
    });
    yield put(push("/genres"));
}

export function* watchUpdateGenre() {
    yield takeEvery(sagaActionTypes.GENRE_ELEMENT_UPDATE, workerUpdateGenre);
}

export function* workerAddGenre() {
    const genre = yield select(genreSelector);
    yield call(fetch, "/api/genres", {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify(genre)
    });
    yield put(push("/genres"));
}

export function* watchAddGenre() {
    yield takeEvery(sagaActionTypes.GENRE_ELEMENT_ADD, workerAddGenre);
}

const genreToDeleteIdSelector = (state: any) => state.genre.elementToDeleteId;

export function* workerRemoveGenre() {
    const id = yield select(genreToDeleteIdSelector);
    yield call(fetch, "/api/genres/" + id, {
        method: "DELETE",
    });
    yield put(push("/genres"));
}

export function* watchRemoveGenre() {
    yield takeEvery(sagaActionTypes.GENRE_ELEMENT_REMOVE, workerRemoveGenre);
}
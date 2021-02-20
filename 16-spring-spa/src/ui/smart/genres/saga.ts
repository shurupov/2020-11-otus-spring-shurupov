import {call, put, takeEvery} from "redux-saga/effects";
import {sagaActionTypes} from "store/sagaActionTypes";
import {genreSlice} from "smart/genres/slice";
import {crumbsSlice} from "smart/breadCrumbs/slice";

export const openGenreListAction = () => {
    return {
        type: sagaActionTypes.GENRE_LIST_DISPLAY,
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
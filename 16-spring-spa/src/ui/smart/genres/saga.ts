import {call, put, takeEvery} from "redux-saga/effects";
import {sagaActionTypes} from "store/sagaActionTypes";
import {genreSlice} from "smart/genres/slice";

export const displayGenreListAction = () => {
    return {
        type: sagaActionTypes.GENRE_LIST_DISPLAY,
    };
};

export function* workerDisplayGenreList() {
    const response = yield call(fetch, "/api/genres");
    const genres = yield call([response, 'json']);
    yield put(genreSlice.actions.list(genres));
}

export function* watchDisplayGenreList() {
    yield takeEvery(sagaActionTypes.GENRE_LIST_DISPLAY, workerDisplayGenreList);
}
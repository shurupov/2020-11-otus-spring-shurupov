import {connect} from "react-redux";
import GenreEditor from "components/genre/GenreEditor";
import {Dispatch} from "redux";
import {EditorType} from "../../utils/EditorType";
import {Genre} from "components/genre/GenreList";
import {genreSlice} from "smart/genres/slice";
import {addGenreAction, updateGenreAction} from "smart/genres/saga";

const mapStateToProps = (storeState: any) => {
    return {
        genre: storeState.genre.element,
        type: storeState.genre.editorType
    }
};

const mapDispatchToProps = (dispatch: Dispatch) => {
    return {
        updateView: (genre: Genre) => {
            dispatch(genreSlice.actions.openElement(genre));
        },
        update: (type: EditorType) => {
            if (type == EditorType.EDIT) {
                dispatch(updateGenreAction());
            } else {
                dispatch(addGenreAction());
            }
        }
    };
};

//@ts-ignore
export const ConnectedGenreEditor = connect(mapStateToProps, mapDispatchToProps)(GenreEditor);
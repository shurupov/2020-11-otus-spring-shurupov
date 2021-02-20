import {connect} from "react-redux";
import BookEditView, {EditorType} from "components/book/BookEditView";
import {Dispatch} from "redux";
import {bookSlice} from "smart/book/slice";
import {Book} from "components/book/BookList";
import {addBookAction, updateBookAction} from "smart/book/saga";

const mapStateToProps = (storeState: any) => {
    return {
        book: storeState.book.element,
        genres: storeState.genre.list,
        authors: storeState.author.list,
        type: storeState.book.editorType
    }
};

const mapDispatchToProps = (dispatch: Dispatch) => {
    return {
        updateView: (book: Book) => {
            dispatch(bookSlice.actions.updateElementView(book));
        },
        update: (type: EditorType) => {
            if (type == EditorType.EDIT) {
                dispatch(updateBookAction());
            } else {
                dispatch(addBookAction());
            }
        }
    };
};

//@ts-ignore
export const ConnectedBookEditView = connect(mapStateToProps, mapDispatchToProps)(BookEditView);
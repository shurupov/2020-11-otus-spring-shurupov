import {connect} from "react-redux";
import BookEditor, {EditorType} from "components/book/BookEditor";
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
            dispatch(bookSlice.actions.openElement(book));
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
export const ConnectedBookEditor = connect(mapStateToProps, mapDispatchToProps)(BookEditor);
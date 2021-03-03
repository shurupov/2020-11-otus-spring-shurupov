import {connect} from "react-redux";
import BookEditor from "components/book/BookEditor";
import {Dispatch} from "redux";
import {bookSlice} from "smart/book/slice";
import {Book} from "components/book/BookList";
import {addBookAction, updateBookAction} from "smart/book/saga";
import {EditorType} from "../../utils/EditorType";

const mapStateToProps = (storeState: any) => {
    return {
        book: storeState.book.element,
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
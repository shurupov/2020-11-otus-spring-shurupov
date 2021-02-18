import {connect} from "react-redux";
import BookEditView from "components/book/BookEditView";
import {Dispatch} from "redux";
import {bookSlice} from "smart/book/slice";
import {Book} from "components/book/BookList";

const mapStateToProps = (storeState: any) => {
    console.log("ConnectedBookEditView", storeState);
    return {
        book: storeState.book.element,
        genres: storeState.genre.list,
        authors: storeState.author.list,
    }
};

const mapDispatchToProps = (dispatch: Dispatch) => {
    return {
        updateView: (book: Book) => {
            dispatch(bookSlice.actions.updateElementView(book));
        },
    };
};

//@ts-ignore
export const ConnectedBookEditView = connect(mapStateToProps, mapDispatchToProps)(BookEditView);
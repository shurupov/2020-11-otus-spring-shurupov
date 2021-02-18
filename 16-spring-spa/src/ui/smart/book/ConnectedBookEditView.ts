import {connect} from "react-redux";
import BookEditView from "components/book/BookEditView";

const mapStateToProps = (storeState: any) => {
    return {
        book: storeState.book.element,
        genres: storeState.genre.list,
        authors: storeState.author.list,
    }
};

//@ts-ignore
export const ConnectedBookEditView = connect(mapStateToProps)(BookEditView);
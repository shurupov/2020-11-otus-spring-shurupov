import {connect} from "react-redux";
import BookList from "components/book/BookList";

const mapStateToProps = (storeState: any) => {
    return {
        books: storeState.book.list
    }
};

export const ConnectedBookList = connect(mapStateToProps)(BookList);
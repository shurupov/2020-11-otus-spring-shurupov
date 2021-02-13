import {connect} from "react-redux";
import BookList from "components/book/BookList";
import {StoreState} from "store/reducer";

const mapStateToProps = (storeState: StoreState) => {
    console.log("mapStateToProps", storeState);
    return {
        books: storeState.book.list
    }
};

export const ConnectedBookList = connect(mapStateToProps)(BookList);
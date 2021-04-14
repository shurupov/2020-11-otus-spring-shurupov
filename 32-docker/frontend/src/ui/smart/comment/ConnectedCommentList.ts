import {connect} from "react-redux";
import CommentList from "components/comment/CommentList";

const mapStateToProps = (storeState: any) => {
    return {
        bookId: storeState.book.element.id,
        comments: storeState.comment.list,
    }
};

export const ConnectedCommentList = connect(mapStateToProps)(CommentList);
import {connect} from "react-redux";
import CommentEditor from "../../components/comment/CommentEditor";
import {Dispatch} from "redux";
import {EditorType} from "../../utils/EditorType";
import {Comment} from "../../components/comment/CommentList";
import {commentSlice} from "./slice";
import {addCommentAction, updateCommentAction} from "./saga";

const mapStateToProps = (storeState: any) => {
    return {
        comment: storeState.comment.element,
        type: storeState.comment.editorType
    }
};

const mapDispatchToProps = (dispatch: Dispatch) => {
    return {
        updateView: (comment: Comment) => {
            dispatch(commentSlice.actions.openElement(comment));
        },
        update: (type: EditorType) => {
            if (type == EditorType.EDIT) {
                dispatch(updateCommentAction());
            } else {
                dispatch(addCommentAction());
            }
        }
    };
};

//@ts-ignore
export const ConnectedCommentEditor = connect(mapStateToProps, mapDispatchToProps)(CommentEditor);
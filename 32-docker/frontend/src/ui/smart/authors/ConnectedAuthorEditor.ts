import {connect} from "react-redux";
import AuthorEditor from "components/author/AuthorEditor";
import {Dispatch} from "redux";
import {EditorType} from "../../utils/EditorType";
import {Author} from "components/author/AuthorList";
import {authorSlice} from "smart/authors/slice";
import {addAuthorAction, updateAuthorAction} from "smart/authors/saga";

const mapStateToProps = (storeState: any) => {
    return {
        author: storeState.author.element,
        type: storeState.author.editorType
    }
};

const mapDispatchToProps = (dispatch: Dispatch) => {
    return {
        updateView: (author: Author) => {
            dispatch(authorSlice.actions.openElement(author));
        },
        update: (type: EditorType) => {
            if (type == EditorType.EDIT) {
                dispatch(updateAuthorAction());
            } else {
                dispatch(addAuthorAction());
            }
        }
    };
};

//@ts-ignore
export const ConnectedAuthorEditor = connect(mapStateToProps, mapDispatchToProps)(AuthorEditor);
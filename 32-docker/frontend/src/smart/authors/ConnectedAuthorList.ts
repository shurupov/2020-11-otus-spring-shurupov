import {connect} from "react-redux";
import AuthorList from "../../components/author/AuthorList";

const mapStateToProps = (storeState: any) => {
    return {
        authors: storeState.author.list
    }
};

export const ConnectedAuthorList = connect(mapStateToProps)(AuthorList);
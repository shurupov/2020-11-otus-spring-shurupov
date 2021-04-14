import {connect} from "react-redux";
import GenreList from "../../components/genre/GenreList";

const mapStateToProps = (storeState: any) => {
    return {
        genres: storeState.genre.list
    }
};

export const ConnectedGenreList = connect(mapStateToProps)(GenreList);
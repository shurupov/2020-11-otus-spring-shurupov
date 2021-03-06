import React from 'react'
import { Provider } from "react-redux";
import {Container} from "@material-ui/core";
import 'fontsource-roboto';
import {Route, Switch} from "react-router-dom";
import NavigationPanel from "components/common/NavigationPanel";
import {store, history} from "store/store";
import { ConnectedBookList } from 'smart/book/ConnectedBookList';
import {ConnectedRouter} from "connected-react-router";
import { ConnectedBookEditor } from 'smart/book/ConnectedBookEditor';
import { ConnectedCrumbs } from 'smart/breadCrumbs/ConnectedCrumbs';
import { ConnectedSummary } from 'smart/summary/ConnectedSummary';
import {ConnectedGenreList} from "smart/genre/ConnectedGenreList";
import {ConnectedGenreEditor} from "smart/genre/ConnectedGenreEditor";
import {ConnectedAuthorList} from "smart/authors/ConnectedAuthorList";
import {ConnectedAuthorEditor} from "smart/authors/ConnectedAuthorEditor";
import {ConnectedCommentEditor} from "smart/comment/ConnectedCommentEditor";
import {ConnectedCommentList} from "smart/comment/ConnectedCommentList";
import Login from "components/login/Login";
import {ConnectedLogin} from "smart/login/ConnectedLogin";

interface Book {
    id: number;
    name: string;
    author: string;
    genres: string
}

export default class App extends React.Component {

    render() {
        return (
            <Provider store={store}>
                <Container maxWidth="md">
                    <ConnectedRouter history={history}>
                        <NavigationPanel />
                        <ConnectedCrumbs />
                        <Switch>
                            <Route path="/" exact component={ConnectedSummary} />

                            <Route path="/auth" exact component={ConnectedLogin} />

                            <Route path="/books" exact component={ConnectedBookList} />
                            <Route path="/books/add" exact component={ConnectedBookEditor} />
                            <Route path="/books/:id" exact >
                                <ConnectedBookEditor />
                                <br />
                                <ConnectedCommentList />
                            </Route>
                            <Route path="/books/:bookId/comments/add" exact>
                                <ConnectedBookEditor />
                                <br />
                                <ConnectedCommentEditor />
                            </Route>
                            <Route path="/books/:bookId/comments/:id" exact>
                                <ConnectedBookEditor />
                                <br />
                                <ConnectedCommentEditor />
                            </Route>

                            <Route path="/genres" exact component={ConnectedGenreList} />
                            <Route path="/books/add" exact component={ConnectedGenreEditor} />
                            <Route path="/genres/:id" exact component={ConnectedGenreEditor} />

                            <Route path="/authors" exact component={ConnectedAuthorList} />
                            <Route path="/authors/add" exact component={ConnectedAuthorEditor} />
                            <Route path="/authors/:id" exact component={ConnectedAuthorEditor} />

                        </Switch>
                    </ConnectedRouter>
                </Container>
            </Provider>
        )
    }
}

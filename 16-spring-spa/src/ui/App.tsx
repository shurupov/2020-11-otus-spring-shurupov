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
import {ConnectedGenreList} from "smart/genres/ConnectedGenreList";

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

                            <Route path="/books" exact component={ConnectedBookList} />
                            <Route path="/books/add" exact component={ConnectedBookEditor} />
                            <Route path="/books/:id" exact component={ConnectedBookEditor} />

                            <Route path="/genres" exact component={ConnectedGenreList} />
                            {/*<Route path="/books/add" exact component={ConnectedBookEditor} />*/}
                            {/*<Route path="/books/:id" exact component={ConnectedBookEditor} />*/}

                        </Switch>
                    </ConnectedRouter>
                </Container>
            </Provider>
        )
    }
}
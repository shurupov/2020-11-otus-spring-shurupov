import React from 'react'
import { Provider } from "react-redux";
import {Container} from "@material-ui/core";
import 'fontsource-roboto';
import {Route, Switch} from "react-router-dom";
import Summary from "components/Summary";
import NavigationPanel from "components/common/NavigationPanel";
import Crumbs from "components/common/Crumbs";
import {store, history} from "store/store";
import { ConnectedBookList } from 'smart/book/ConnectedBookList';
import {ConnectedRouter} from "connected-react-router";
import { ConnectedBookEditView } from 'smart/book/ConnectedBookEditView';
import { ConnectedCrumbs } from 'smart/breadCrumbs/ConnectedCrumbs';

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
                            <Route path="/books" exact>
                                <ConnectedBookList />
                            </Route>
                            <Route path="/books/add" exact>
                                <ConnectedBookEditView />
                            </Route>
                            <Route path="/books/:id" exact>
                                <ConnectedBookEditView />
                            </Route>
                            <Route path="/" exact>
                                <Summary counts={{
                                    books: 5,
                                    comments: 4,
                                    authors: 6,
                                    genres: 8
                                }} />
                            </Route>
                        </Switch>
                    </ConnectedRouter>
                </Container>
            </Provider>
        )
    }
}

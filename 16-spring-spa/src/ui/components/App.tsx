import React from 'react'
import { Provider } from "react-redux";
import {Container} from "@material-ui/core";
import 'fontsource-roboto';
import {Route, Switch} from "react-router-dom";
import Summary from "./Summary";
import NavigationPanel from "./common/NavigationPanel";
import Crumbs from "./common/Crumbs";
import {store, history} from "store/store";
import { ConnectedBookList } from 'smart/book/ConnectedBookList';
import {ConnectedRouter} from "connected-react-router";

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
                        <Switch>
                            <Route path="/books">
                                <Crumbs />
                                <ConnectedBookList />
                            </Route>
                            <Route path="/" exact>
                                <Crumbs />
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

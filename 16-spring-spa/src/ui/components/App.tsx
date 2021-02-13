import React from 'react'
import { Provider } from "react-redux";
import {Container} from "@material-ui/core";
import 'fontsource-roboto';
import BookList from "./book/BookList";
import {BrowserRouter, Route, Switch} from "react-router-dom";
import Summary from "./Summary";
import NavigationPanel from "./common/NavigationPanel";
import Crumbs from "./common/Crumbs";
import {store} from "store/store";
import { ConnectedBookList } from 'smart/book/ConnectedBookList';

interface Book {
    id: number;
    name: string;
    author: string;
    genres: string
}

export default class App extends React.Component<any, { books: Array<Book> }> {

    constructor(props: any) {
        super(props);
        this.state = {books: []};
    }

    componentDidMount() {
        fetch('/api/books')
            .then(response => response.json())
            .then(books => this.setState({books}));
    }

    render() {
        return (
            <Provider store={store}>
                <Container maxWidth="md">
                    <BrowserRouter>
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
                    </BrowserRouter>
                </Container>
            </Provider>
        )
    }
}

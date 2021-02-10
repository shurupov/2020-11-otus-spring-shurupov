import React from 'react'
import {Container} from "@material-ui/core";
import 'fontsource-roboto';
import BookList from "./book/BookList";
import {BrowserRouter, Route, Switch} from "react-router-dom";
import Summary from "./Summary";

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
            <Container maxWidth="md">
                <BrowserRouter>
                    <Switch>
                        <Route path="/books">
                            <BookList books={this.state.books} />
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
                </BrowserRouter >
            </Container>
        )
    }
}

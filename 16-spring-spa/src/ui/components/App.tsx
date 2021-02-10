import React from 'react'
import {Container} from "@material-ui/core";
import 'fontsource-roboto';
import BookList from "./book/BookList";

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
                <BookList books={this.state.books} />
            </Container>
        )
    }
}

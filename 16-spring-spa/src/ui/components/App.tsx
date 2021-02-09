import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import {ItemsTable} from "./ItemsTable";

interface Book {
    id: number;
    name: string;
    author: string;
    genres: string
}

interface HeaderProps {
    title: string;
}

const Header = (props: HeaderProps) => (
    <h1>{props.title}</h1>
);

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
            <React.Fragment>
                <Header title={'Books'}/>
                <ItemsTable data={this.state.books} />
                {/*<Table header={["#", "Name", "Author", "Genres"]} data={this.state.books.map((book) => [book.id, book.name, book.author, book.genres])} />*/}
            </React.Fragment>
        )
    }
}

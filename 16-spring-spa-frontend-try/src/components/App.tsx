import React from 'react'

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
        fetch('/books')
            .then(response => response.json())
            .then(books => this.setState({books}));
    }

    render() {
        return (
            <React.Fragment>
                <Header title={'Books'}/>
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Author</th>
                        <th>Genres</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.books.map((book, i) => (
                            <tr key={i}>
                                <td>{book.id}</td>
                                <td>{book.name}</td>
                                <td>{book.author}</td>
                                <td>{book.genres}</td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
            </React.Fragment>
        )
    }
}

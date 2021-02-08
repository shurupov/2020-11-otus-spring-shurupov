import React from 'react'

const Header = (props) => (
    <h1>{props.title}</h1>
);

export default class App extends React.Component {

    constructor() {
        super();
        this.state = {persons: []};
    }

    componentDidMount() {
        fetch('/books')
            .then(response => response.json())
            .then(persons => this.setState({persons}));
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
                        this.state.persons.map((book, i) => (
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
};

import React, {FormEvent} from "react";
import {
    Checkbox,
    FormControl,
    FormControlLabel, FormGroup,
    InputLabel,
    MenuItem,
    Select,
    TextField, Typography
} from "@material-ui/core";

interface Book {
    name: string;
    authorId: number;
    genres: Array<number>;
}

interface Genre {
    id: number;
    name: string;
}

interface Author {
    id: number;
    name: string;
}

export interface BookEditProps {
    book: Book;
    genres: Array<Genre>;
    authors: Array<Author>;
}

export default class BookEditView extends React.Component<BookEditProps, Book> {

    constructor(props: BookEditProps) {
        super(props);
        this.state = this.props.book;
        this.handleChange = this.handleChange.bind(this);
    }

    handleSubmit(event: FormEvent) {
        console.log(event);
        event.preventDefault();
    }

    componentDidUpdate(prevProps: Readonly<any>, prevState: Readonly<Book>, snapshot?: any) {
        console.log(this.state);
    }

    handleChange(event: FormEvent<any>) {
        const element = event.target as HTMLFormElement;

        if (element.name == "genres") {
            const genreId = parseFloat(element.id.substr(5));
            console.log(genreId);
            this.setState((state) => {
                if (element.checked && !state.genres.includes(genreId)) {
                    const genres = state.genres.slice();
                    console.log(genres);
                    genres.push(genreId);
                    console.log(genres);
                    return {
                        ...state,
                        genres
                    }
                }
                if (!element.checked && state.genres.includes(genreId)) {
                    return {
                        ...state,
                        genres: state.genres.filter(id => id != genreId)
                    }
                }
                return {...state}
            });
        } else {
            this.setState((state) => ({
                ...state,
                [element.name]: element.value
            }));
        }
    }

    render() {

        console.log(this.state.genres);
        console.log(this.props.genres);

        return (
            <form noValidate autoComplete="off" onSubmit={this.handleSubmit}>
                <TextField
                    label="Name"
                    name="name"
                    variant="outlined"
                    fullWidth
                    onChange={this.handleChange}
                    value={this.state.name}
                    margin={"normal"}
                />

                <FormControl variant="outlined" fullWidth margin={"normal"}>
                    <InputLabel id="demo-simple-select-outlined-label">Author</InputLabel>
                    <Select
                        value={this.state.authorId}
                        name="authorId"
                        onChange={this.handleChange}
                        label="Author"
                    >
                        {this.props.authors.map((author:any) => <MenuItem key={author.id} value={author.id}>{author.firstName + " " + author.lastName}</MenuItem>)}
                    </Select>
                </FormControl>

                <Typography>Genres</Typography>
                <FormGroup row>
                    {
                        this.props.genres.map((genre:any) => (
                            <FormControlLabel
                                key={genre.id}
                                control={
                                    <Checkbox
                                        key={genre.id}
                                        id={"genre" + genre.id}
                                        checked={this.state.genres.includes(genre.id)}
                                        onChange={this.handleChange}
                                        name="genres"
                                        color="primary"
                                    />
                                }
                                label={genre.name}
                            />
                        ))
                    }
                </FormGroup>
            </form>
        );
    }
}
import React, {FormEvent} from "react";
import {
    Button,
    Checkbox,
    FormControl,
    FormControlLabel, FormGroup,
    InputLabel,
    MenuItem, Paper,
    Select,
    TextField, Typography
} from "@material-ui/core";
import {EditorType} from "../../utils/EditorType";

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
    firstName: string;
    lastName: string;
}

export interface BookEditorProps {
    book: Book;
    genres: Array<Genre>;
    authors: Array<Author>;
    updateView: Function;
    update: Function;
    type: EditorType;
}

export default class BookEditor extends React.Component<BookEditorProps, Book> {

    constructor(props: BookEditorProps) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event: FormEvent) {
        event.preventDefault();
        this.props.update(this.props.type);
    }

    handleChange(event: FormEvent<any>) {
        const element = event.target as HTMLFormElement;

        if (element.name == "genres") {
            const genreId = parseFloat(element.id.substr(5));
            if (element.checked && !this.props.book.genres.includes(genreId)) {
                const genres = this.props.book.genres.slice();
                genres.push(genreId);
                this.props.updateView({
                    ...this.props.book,
                    genres
                });
            } else if (!element.checked && this.props.book.genres.includes(genreId)) {
                this.props.updateView({
                    ...this.props.book,
                    genres: this.props.book.genres.filter(id => id != genreId)
                });
            }
        } else {
            this.props.updateView({
                ...this.props.book,
                [element.name]: element.value
            });
        }
    }

    render() {
        return (
            <form noValidate autoComplete="off" onSubmit={this.handleSubmit}>
                <TextField
                    label="Name"
                    name="name"
                    variant="outlined"
                    fullWidth
                    onChange={this.handleChange}
                    value={this.props.book.name}
                    margin={"normal"}
                />

                <FormControl variant="outlined" fullWidth margin={"normal"}>
                    <InputLabel id="demo-simple-select-outlined-label">Author</InputLabel>
                    <Select
                        value={this.props.book.authorId}
                        name="authorId"
                        onChange={this.handleChange}
                        label="Author"
                    >
                        {this.props.authors.map((author:any) => <MenuItem key={author.id} value={author.id}>{author.firstName + " " + author.lastName}</MenuItem>)}
                    </Select>
                </FormControl>

                <Paper variant="outlined" style={{ padding: 10, marginTop: 10 }}>
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
                                            checked={this.props.book.genres.includes(genre.id)}
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
                </Paper>
                <Button variant="contained" color="primary" type="submit" style={{ marginTop: 10, marginBottom: 10, float: "right" }}>
                    Save
                </Button>
            </form>
        );
    }
}
import React, {FormEvent} from "react";
import {
    Button,
    FormControl,
    FormGroup, IconButton,
    InputLabel,
    MenuItem, Paper,
    Select,
    TextField, Typography
} from "@material-ui/core";
import {EditorType} from "../../utils/EditorType";
import {Delete as DeleteIcon} from "@material-ui/icons";

interface Book {
    name: string;
    authorId: number;
    genres: Array<string>;
    comments: Array<string>;
}

interface Author {
    id: number;
    firstName: string;
    lastName: string;
}

export interface BookEditorProps {
    book: Book;
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
        this.addGenre = this.addGenre.bind(this);
        this.addComment = this.addComment.bind(this);
    }

    handleSubmit(event: FormEvent) {
        event.preventDefault();
        this.props.update(this.props.type);
    }

    addGenre() {
        this.props.updateView({
            ...this.props.book,
            genres: [...this.props.book.genres.slice(), ""],
        });
    }

    removeGenre(i: number) {
        this.props.updateView({
            ...this.props.book,
            genres: [...this.props.book.genres.slice(0, i), ...this.props.book.genres.slice(i + 1)],
        });
    }

    addComment() {
        this.props.updateView({
            ...this.props.book,
            comments: [...this.props.book.comments.slice(), ""]
        });
    }

    removeComment(i: number) {
        this.props.updateView({
            ...this.props.book,
            comments: [...this.props.book.comments.slice(0, i), ...this.props.book.comments.slice(i + 1)],
        });
    }

    handleChange(event: FormEvent<any>) {
        const element = event.target as HTMLFormElement;

        if (element.name == "genres") {
            const genreIndex = parseFloat(element.id.substr(5));
            const genres = this.props.book.genres.slice();
            genres[genreIndex] = element.value;
            this.props.updateView({
                ...this.props.book,
                genres
            });
        } else if (element.name == "comments") {
            const commentIndex = parseFloat(element.id.substr(7));
            const comments = this.props.book.comments.slice();
            comments[commentIndex] = element.value;
            this.props.updateView({
                ...this.props.book,
                comments
            });
        } else {
            this.props.updateView({
                ...this.props.book,
                [element.name]: element.value
            });
        }
    }

    render() {
        return (
            <form noValidate autoComplete="off" onSubmit={this.handleSubmit} style={{ clear: "both" }}>
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
                    {this.props.book.genres.map((genre:string, i: number) => (
                        <FormGroup row key={i}>
                            <TextField
                                id={"genre" + i}
                                label={"Genre " + (i + 1)}
                                name="genres"
                                variant="outlined"
                                onChange={this.handleChange}
                                value={genre}
                                margin={"normal"}
                            />
                            <IconButton aria-label="delete">
                                <DeleteIcon onClick={() => this.removeGenre(i)} />
                            </IconButton>
                        </FormGroup>
                    ))}
                    <Button variant="contained" color="primary" onClick={this.addGenre} style={{ marginTop: 10, marginBottom: 10 }}>
                        Add Genre
                    </Button>
                </Paper>

                <Paper variant="outlined" style={{ padding: 10, marginTop: 10 }}>
                    <Typography>Comments</Typography>
                    {this.props.book.comments.map((comment:string, i: number) => (
                        <FormGroup row key={i}>
                            <TextField
                                id={"comment" + i}
                                label={"Comment " + (i + 1)}
                                name="comments"
                                variant="outlined"
                                onChange={this.handleChange}
                                value={comment}
                                margin={"normal"}
                            />
                            <IconButton aria-label="delete">
                                <DeleteIcon onClick={() => this.removeComment(i)} />
                            </IconButton>
                        </FormGroup>
                    ))}
                    <Button variant="contained" color="primary" onClick={this.addComment} style={{ marginTop: 10, marginBottom: 10 }}>
                        Add Comment
                    </Button>
                </Paper>

                <Button variant="contained" color="primary" type="submit" style={{ marginTop: 10, marginBottom: 10, float: "right" }}>
                    Save
                </Button>
            </form>
        );
    }
}
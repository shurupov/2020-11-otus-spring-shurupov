import React, {ChangeEvent, FormEvent} from "react";
import {
    Checkbox, Chip,
    FormControl,
    FormControlLabel, FormGroup,
    Input,
    InputLabel,
    MenuItem,
    Select,
    TextField, Typography
} from "@material-ui/core";

interface BookEditState {
    name: string;
    authorId: number;
    genres: Array<number>;
}

interface Genre {
    id: number;
    title: string;
}

interface Author {
    id: number;
    name: string;
}

export interface BookEditProps {
    genres: Array<Genre>;
    authors: Array<Author>;
    defaultState: BookEditState;
}

export default class BookEdit extends React.Component<BookEditProps, BookEditState> {

    state = this.props.defaultState;

    handleSubmit(event: FormEvent) {
        console.log(event);
        event.preventDefault();
    }

    componentDidUpdate(prevProps: Readonly<any>, prevState: Readonly<BookEditState>, snapshot?: any) {
        console.log(this.state);
    }

    handleChange = (event: FormEvent<{ value: unknown }>) => {
        const element = event.target as HTMLFormElement;

        if (element.name == "genres") {
            const genreId = parseFloat(element.id.substr(5));
            this.setState((state) => {
                if (element.checked && !state.genres.includes(genreId)) {
                    const genres = state.genres.slice();
                    genres.push(genreId);
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

        return (
            <form noValidate autoComplete="off" onSubmit={this.handleSubmit}>
                <TextField
                    id="outlined-basic"
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
                        labelId="demo-simple-select-outlined-label"
                        id="demo-simple-select-outlined"
                        value={this.state.authorId}
                        name="authorId"
                        onChange={this.handleChange}
                        label="Author"
                    >
                        {this.props.authors.map((author) => <MenuItem value={author.id}>{author.name}</MenuItem>)}
                    </Select>
                </FormControl>

                <Typography>Genres</Typography>
                <FormGroup row>
                    {
                        this.props.genres.map((genre) => (
                            <FormControlLabel
                                control={
                                    <Checkbox
                                        id={"genre" + genre.id}
                                        // @ts-ignore
                                        checked={this.state.genres.includes(genre.id)}
                                        onChange={this.handleChange}
                                        name="genres"
                                        color="primary"
                                    />
                                }
                                label={genre.title}
                            />
                        ))
                    }
                </FormGroup>

            </form>
        );
    }
}
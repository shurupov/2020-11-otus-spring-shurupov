import React, {FormEvent} from "react";
import {
    Button,
    TextField
} from "@material-ui/core";
import {EditorType} from "../../utils/EditorType";

interface Genre {
    id: number;
    name: string;
}

export interface GenreEditorProps {
    genre: Genre;
    updateView: Function;
    update: Function;
    type: EditorType;
}

export default class GenreEditor extends React.Component<GenreEditorProps, Genre> {

    constructor(props: GenreEditorProps) {
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

        this.props.updateView({
            ...this.props.genre,
            [element.name]: element.value
        });
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
                    value={this.props.genre.name}
                    margin={"normal"}
                />
                <Button variant="contained" color="primary" type="submit" style={{ marginTop: 10, marginBottom: 10, float: "right" }}>
                    Save
                </Button>
            </form>
        );
    }
}
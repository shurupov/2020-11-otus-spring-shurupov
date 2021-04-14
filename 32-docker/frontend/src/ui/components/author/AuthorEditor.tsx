import React, {FormEvent} from "react";
import {
    Button,
    TextField
} from "@material-ui/core";
import {EditorType} from "../../utils/EditorType";

interface Author {
    id: number;
    firstName: string;
    lastName: string;
}

export interface AuthorEditorProps {
    author: Author;
    updateView: Function;
    update: Function;
    type: EditorType;
}

export default class AuthorEditor extends React.Component<AuthorEditorProps, Author> {

    constructor(props: AuthorEditorProps) {
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
            ...this.props.author,
            [element.name]: element.value
        });
    }

    render() {
        return (
            <form noValidate autoComplete="off" onSubmit={this.handleSubmit}>
                <TextField
                    label="First Name"
                    name="firstName"
                    variant="outlined"
                    fullWidth
                    onChange={this.handleChange}
                    value={this.props.author.firstName}
                    margin={"normal"}
                />
                <TextField
                    label="Last Name"
                    name="lastName"
                    variant="outlined"
                    fullWidth
                    onChange={this.handleChange}
                    value={this.props.author.lastName}
                    margin={"normal"}
                />
                <Button variant="contained" color="primary" type="submit" style={{ marginTop: 10, marginBottom: 10, float: "right" }}>
                    Save
                </Button>
            </form>
        );
    }
}
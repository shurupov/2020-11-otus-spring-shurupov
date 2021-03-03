import React, {FormEvent} from "react";
import {
    Button,
    TextField
} from "@material-ui/core";
import {EditorType} from "../../utils/EditorType";

interface Comment {
    id: number;
    text: string;
}

export interface CommentEditorProps {
    comment: Comment;
    updateView: Function;
    update: Function;
    type: EditorType;
}

export default class CommentEditor extends React.Component<CommentEditorProps, Comment> {

    constructor(props: CommentEditorProps) {
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
            ...this.props.comment,
            [element.name]: element.value
        });
    }

    render() {
        return (
            <form noValidate autoComplete="off" onSubmit={this.handleSubmit}>
                <TextField
                    label="Comment Text"
                    name="text"
                    variant="outlined"
                    fullWidth
                    onChange={this.handleChange}
                    value={this.props.comment.text}
                    margin={"normal"}
                />
                <Button variant="contained" color="primary" type="submit" style={{ marginTop: 10, marginBottom: 10, float: "right" }}>
                    Save
                </Button>
            </form>
        );
    }
}
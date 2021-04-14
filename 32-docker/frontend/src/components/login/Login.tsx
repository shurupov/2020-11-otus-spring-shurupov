import React, {FormEvent} from "react";
import {Button, TextField} from "@material-ui/core";
import {BookEditorProps} from "../book/BookEditor";

export interface LoginState {
    username: string;
    password: string;
}

interface LoginProps {
    login: Function;
}

export default class Login extends React.Component<LoginProps, LoginState> {

    constructor(props: LoginProps) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    state = {
        username: "",
        password: "",
    };

    handleChange(event: FormEvent) {
        const element = event.target as HTMLFormElement;
        this.setState((state) => {
            return {
                ...state,
                [element.name]: element.value
            }
        });
    }

    handleSubmit(event: FormEvent) {
        event.preventDefault();
        this.props.login(this.state);
    }

    render() {
        return (
            <form style={{ paddingLeft: "30%", paddingRight: "30%", paddingTop: 50 }} onSubmit={this.handleSubmit}>
                <div>
                    <TextField
                        label="Username"
                        id="username"
                        name="username"
                        defaultValue=""
                        variant="outlined"
                        fullWidth
                        style={{ margin: 10 }}
                        onChange={this.handleChange}
                    />
                    <TextField
                        label="Password"
                        type="password"
                        id="password"
                        name="password"
                        defaultValue=""
                        variant="outlined"
                        fullWidth
                        style={{ margin: 10 }}
                        onChange={this.handleChange}
                    />
                    <Button
                        variant="contained"
                        color="primary"
                        type="submit"
                        fullWidth
                        style={{ margin: 10 }}
                    >
                        Log In
                    </Button>
                </div>
            </form>
        );
    }
}
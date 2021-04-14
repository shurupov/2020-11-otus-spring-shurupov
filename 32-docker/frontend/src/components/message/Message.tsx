import React from "react";
import {Snackbar} from "@material-ui/core";

interface MessageProps {
    text: string;
    close: Function;
    visible: boolean;
}

export default class Message extends React.Component<MessageProps> {

    handleClose = () => {
        this.props.close();
    }

    render() {
        return (
            <Snackbar
                open={this.props.visible}
                autoHideDuration={1500}
                onClose={() => this.props.close()}
                message={this.props.text}
            />
        );
    }
}
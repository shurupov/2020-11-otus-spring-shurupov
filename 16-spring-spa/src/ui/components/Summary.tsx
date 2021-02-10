import React from "react";
import {Avatar, List, ListItem, ListItemAvatar, ListItemText} from "@material-ui/core";
import {MenuBook, Comment, PermIdentity, Movie} from "@material-ui/icons";
import * as _ from "lodash";

export interface SummaryProps {
    counts: {[key: string]: number;}
}

const icons: {[key: string]: React.ReactElement} = {
    books: <MenuBook />,
    comments: <Comment />,
    author: <PermIdentity />,
    genres: <Movie />,
};

export default class Summary extends React.Component<SummaryProps> {
    render() {
        return (
            <List component="nav">
                { Object.keys(this.props.counts).map((key) => (
                    <ListItem button component="a" href={"/" + key}>
                        <ListItemAvatar>
                            <Avatar>
                                {icons[key]}
                            </Avatar>
                        </ListItemAvatar>
                        <ListItemText primary={_.startCase(key)} secondary={this.props.counts[key] + " pieces"}/>
                    </ListItem>
                )) }

            </List>
        );
    }
}
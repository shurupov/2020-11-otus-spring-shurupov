import React from "react";
import {Avatar, List, ListItem, ListItemAvatar, ListItemText} from "@material-ui/core";
import {MenuBook, Comment, PermIdentity, Movie} from "@material-ui/icons";
import * as _ from "lodash";
import {Link} from "react-router-dom";

export interface SummaryProps {
    counts: {[key: string]: number;}
}

const icons: {[key: string]: React.ReactElement} = {
    books: <MenuBook />,
    comments: <Comment />,
    authors: <PermIdentity />,
    genres: <Movie />,
};

export default class Summary extends React.Component<SummaryProps> {
    render() {
        return (
            <List component="nav">
                { ["books", "authors", "genres", "comments"].map((key) => (
                    <ListItem
                        key={key}
                        component={Link}
                        button
                        to={
                            key == "comments"
                                ? "/"
                                : "/" + key
                        }
                        style={{ textDecoration: "none", color: "inherit" }}
                    >
                        <ListItemAvatar>
                            <Avatar>
                                {icons[key]}
                            </Avatar>
                        </ListItemAvatar>
                        <ListItemText secondary={this.props.counts[key] + " pieces"}>
                            {_.startCase(key)}
                        </ListItemText>
                    </ListItem>
                )) }

            </List>
        );
    }
}
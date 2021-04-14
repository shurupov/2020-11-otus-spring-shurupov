import React from "react";
import {Breadcrumbs, Paper, Typography} from "@material-ui/core";
import {Link as RouterLink} from "react-router-dom";

interface Crumb {
    caption: string;
    url: string;
}

export interface CrumbsProps {
    items: Array<Crumb>;
}

export default class Crumbs extends React.Component<CrumbsProps> {
    render() {
        return (
            <Paper variant="outlined" style={{ padding: 10, marginTop: 10 }}>
            <Breadcrumbs aria-label="breadcrumb">
                { this.props.items.map((item, i) => (
                    i < this.props.items.length - 1
                        ? <RouterLink key={i} to={item.url} style={{ color: "inherit", textDecoration: "inherit" }}>{item.caption}</RouterLink>
                        : <Typography key={i} color="textPrimary">{item.caption}</Typography>
                )) }
            </Breadcrumbs>
            </Paper>
        );
    }
}
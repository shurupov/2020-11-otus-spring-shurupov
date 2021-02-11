import React from "react";
import {Breadcrumbs, Paper, Typography} from "@material-ui/core";
import {Link as RouterLink} from "react-router-dom";

interface Crumb {
    caption: string;
    url: string;
}

interface CrumbsProps {
    crumbCaption?: string;
}

const routes: {[key: string]: string} = {
    "/books" : "Books",
    "/comments": "Comments",
    "/authors": "Authors",
    "/genres": "Genres",
};

export default class Crumbs extends React.Component<CrumbsProps> {
    render() {

        const items: Array<Crumb> = [];
        items.push({ caption: "Home", url: "/" });

        const pathnames = location.pathname.split('/').filter((x) => x);

        for (let i = 0; i < pathnames.length; i++) {
            const path = "/" + pathnames.slice(0, i + 1).join("/");
            if (routes.hasOwnProperty(path)) {
                items.push({ caption: routes[path], url: path });
            } else {
                items.push({ caption: this.props.crumbCaption || "Element", url: path });
            }
        }

        return (
            <Paper variant="outlined" style={{ padding: 10, marginTop: 10 }}>
            <Breadcrumbs aria-label="breadcrumb">
                { items.map((item, i) => (
                    i < items.length - 1
                        ? <RouterLink to={item.url} style={{ color: "inherit", textDecoration: "inherit" }}>{item.caption}</RouterLink>
                        : <Typography color="textPrimary">{item.caption}</Typography>
                )) }
            </Breadcrumbs>
            </Paper>
        );
    }
}
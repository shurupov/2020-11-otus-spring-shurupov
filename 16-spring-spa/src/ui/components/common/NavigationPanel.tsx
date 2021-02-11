import React from "react";
import {AppBar, Button, Toolbar, Typography} from "@material-ui/core";
import {NavLink} from "react-router-dom";

const menuItem = (name: string, uri: string, exact = false) => {
    return (
        <Typography variant="h6">
            <NavLink
                to={uri}
                style={{ color: "inherit", textDecoration: "inherit" }}
                activeStyle={{ color: "lightblue" }}
                exact={exact}
            >
                {name}
            </NavLink>&nbsp;
        </Typography>
    );
};

export default function NavigationPanel() {
    return (
        <AppBar position="static">
            <Toolbar>
                { menuItem("Home", "/", true) }
                { menuItem("Books", "/books") }
                { menuItem("Comments", "/comments") }
                { menuItem("Authors", "/authors") }
                { menuItem("Genres", "/genres") }
            </Toolbar>
        </AppBar>
    );
}
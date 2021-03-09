import React from "react";
import {AppBar, Link, Toolbar, Typography} from "@material-ui/core";
import {NavLink} from "react-router-dom";

const menuItem = (name: string, uri: string, exact = false) => {
    return (
        <Typography variant="h6">
            <Link component={NavLink}
                  to={uri}
                  style={{ color: "inherit" }}
                  activeStyle={{ color: "lightblue" }}
                  exact={exact}
            >
                {name}
            </Link>&nbsp;
        </Typography>
    );
};

export default function NavigationPanel() {
    return (
        <AppBar position="static">
            <Toolbar>
                { menuItem("Home", "/", true) }
                { menuItem("Books", "/books") }
                { menuItem("Authors", "/authors") }
                { menuItem("Genres", "/genres") }
                { menuItem("Login", "/auth") }
            </Toolbar>
        </AppBar>
    );
}
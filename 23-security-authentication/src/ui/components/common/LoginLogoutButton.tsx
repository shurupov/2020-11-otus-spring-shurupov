import {NavLink} from "react-router-dom";
import {Button, Link, Typography} from "@material-ui/core";
import React from "react";

interface LoginLogoutButtonProps {
    logged: boolean;
    logout: Function;
}

export const LoginLogoutButton = (props: LoginLogoutButtonProps) => {
    console.log(props);
    return (
        <Typography variant="h6">
            {
                props.logged
                    ? <NavLink
                        to={"#"}
                        onClick={(e) => { e.preventDefault(); props.logout(); }}
                        style={{ color: "inherit", textDecoration: "inherit" }}
                    >Logout</NavLink>
                    : <Link component={NavLink}
                            to={"/auth"}
                            style={{ color: "inherit" }}
                            activeStyle={{ color: "lightblue" }}
                    >
                        Login
                    </Link>
            }
        </Typography>
    );


}
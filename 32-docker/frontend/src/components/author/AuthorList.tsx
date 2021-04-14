import React from "react";
import {DataList} from "../common/DataList";

export interface Author {
    id: number;
    firstName: string;
    lastName: string;
}

export interface AuthorListProps {
    authors: Array<Author>;
}

const columns = ["id", "firstName", "lastName"];
const headers = {id: "#", firstName: "First Name", lastName: "Last Name"};

export default function AuthorList(props: AuthorListProps) {
    return <DataList
        title={"Authors"}
        uriPrefix="/authors/"
        columns={columns}
        headers={headers}
        data={props.authors}
    />
}
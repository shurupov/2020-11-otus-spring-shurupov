import React from "react";
import {DataList} from "../common/DataList";

export interface Book {
    id: number;
    name: string;
    author: string;
    genres: string;
}

export interface BookListProps {
    books: Array<Book>;
}

const columns = ["id", "name", "author", "genres"];
const headers = {id: "#", name: "Name", author: "Author", genres: "Genres"};

export default function BookList(props: BookListProps) {
    return <DataList
        title={"Books"}
        uriPrefix="/books/"
        columns={columns}
        headers={headers}
        data={props.books}
    />
}
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

const columns = ["id", "name", "author", "genres", "comments"];
const headers = {id: "#"};

export default function BookList(props: BookListProps) {
    return <DataList
        title={"Books"}
        uriPrefix="/books/"
        columns={columns}
        headers={headers}
        data={props.books}
        render={{
            author: (author: any) => author.firstName + " " + author.lastName,
            genres: (genres: Array<string>) => genres.join(", "),
            comments: (comments: Array<string>) => comments ? comments.length : 0,
        }}
    />
}
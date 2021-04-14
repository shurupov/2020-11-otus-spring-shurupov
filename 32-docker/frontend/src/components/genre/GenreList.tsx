import React from "react";
import {DataList} from "../common/DataList";

export interface Genre {
    id: number;
    name: string;
}

export interface GenreListProps {
    genres: Array<Genre>;
}

const columns = ["id", "name"];
const headers = {id: "#", name: "Name"};

export default function GenreList(props: GenreListProps) {
    return <DataList
        title={"Genres"}
        uriPrefix="/genres/"
        columns={columns}
        headers={headers}
        data={props.genres}
    />
}
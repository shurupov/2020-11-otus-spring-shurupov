import React from "react";
import {DataList} from "../common/DataList";

export interface Comment {
    id: number;
    name: string;
}

export interface CommentListProps {
    bookId: number;
    comments: Array<Comment>;
}

const columns = ["id", "text"];
const headers = {id: "#", text: "Text"};

export default function CommentList(props: CommentListProps) {
    return <DataList
        title={"Comments"}
        uriPrefix={"/books/" + props.bookId + "/comments/"}
        columns={columns}
        headers={headers}
        data={props.comments}
    />
}
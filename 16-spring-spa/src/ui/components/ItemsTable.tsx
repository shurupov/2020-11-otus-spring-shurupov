import React from "react";
import {Table} from "react-bootstrap";

export interface TableProps {
    data: Array<{}>;
}

export class ItemsTable extends React.Component<TableProps> {
    render() {
        const headers: Array<string> = [];
        if (this.props.data.length > 0) {
            for (const key in this.props.data[0]) {
                headers.push(key);
            }
        }

        return <Table striped bordered hover>
            <thead>
                <tr>
                    { headers.map(h => <th>{h}</th>) }
                </tr>
            </thead>
            <tbody>
                { this.props.data.map(
                    row => <tr>{
                        // @ts-ignore
                        headers.map(field => <td>{row[field]}</td>)
                    }</tr>
                ) }
            </tbody>
        </Table>
    }
}

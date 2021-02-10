import React from "react";
import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@material-ui/core";

export interface TableProps {
    data: Array<object>;
}

export class ItemsTable extends React.Component<TableProps> {
    render() {
        const headers: Array<string> = [];
        if (this.props.data.length > 0) {
            for (const key in this.props.data[0]) {
                headers.push(key);
            }
        }

        return (
            <TableContainer component={Paper}>
                <Table style={{ minWidth: 650 }} size="small" aria-label="a dense table">
                    <TableHead>
                        <TableRow>
                            <TableCell>#</TableCell>
                            <TableCell>Name</TableCell>
                            <TableCell>Author</TableCell>
                            <TableCell>Genres</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {this.props.data.map((row) => (
                            <TableRow key={row.id}>
                                <TableCell component="th" scope="row">
                                    {row.id}
                                </TableCell>
                                <TableCell>{row.name}</TableCell>
                                <TableCell>{row.author}</TableCell>
                                <TableCell>{row.genres}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        );
    }
}

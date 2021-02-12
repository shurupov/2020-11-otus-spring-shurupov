import React from "react";
import * as _ from "lodash";
import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography} from "@material-ui/core";

export interface DataListProps {
    title: string;
    columns: Array<string>;
    headers?: {[key: string]: string;};
    data: Array<any>;
}

export class DataList extends React.Component<DataListProps> {
    render() {

        let headers: {[key: string]: string;};
        if (this.props.headers) {
            headers = {...this.props.headers};
        } else {
            headers = {};
            this.props.columns.forEach(column => { headers[column] = _.startCase(column); });
        }

        return (
            <>
                <Typography variant="h3" component="h3">{this.props.title}</Typography>
                <TableContainer component={Paper}>
                    <Table style={{ minWidth: 650 }} size="medium">
                        <TableHead>
                            <TableRow>
                                {this.props.columns.map((column) => (
                                    <TableCell key={column}>
                                        {headers[column]}
                                    </TableCell>
                                ))}
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {this.props.data.map((row) => (
                                <TableRow key={row.id}>
                                    {this.props.columns.map((column) => (
                                        <TableCell key={column}>
                                            {row[column]}
                                        </TableCell>
                                    ))}
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </>
        );
    }
}

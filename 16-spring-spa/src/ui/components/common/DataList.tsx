import React from "react";
import * as _ from "lodash";
import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography} from "@material-ui/core";
import EditIcon from '@material-ui/icons/Edit';
import DeleteForeverIcon from '@material-ui/icons/DeleteForever';

export interface DataListProps {
    title: string;
    columns: Array<string>;
    headers?: {[key: string]: string;};
    data: Array<any>;
}

export function DataList(props: DataListProps) {

    let headers: { [key: string]: string; };
    if (props.headers) {
        headers = {...props.headers};
    } else {
        headers = {};
        props.columns.forEach(column => {
            headers[column] = _.startCase(column);
        });
    }

    return (
        <>
            <Typography variant="h3" component="h3">{props.title}</Typography>
            <TableContainer component={Paper}>
                <Table style={{minWidth: 650}} size="medium">
                    <TableHead>
                        <TableRow>
                            {props.columns.map((column) => (
                                <TableCell key={column}>
                                    {headers[column]}
                                </TableCell>
                            ))}
                            <TableCell key="actions">
                            </TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {props.data.map((row) => (
                            <TableRow key={row.id}>
                                {props.columns.map((column) => (
                                    <TableCell key={column}>
                                        {row[column]}
                                    </TableCell>
                                ))}
                                <TableCell key={row.id}>
                                    <EditIcon />
                                    <DeleteForeverIcon />
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </>
    );
}

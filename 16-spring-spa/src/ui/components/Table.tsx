import React from "react";

export interface TableProps {
    header: Array<string>;
    data: Array<Array<any>>;
}

export class Table extends React.Component<TableProps> {
    render() {
        return <table>
            <thead>
                <tr>
                    { this.props.header.map( (name, i) => <th key={"table-name-" + i}>{name}</th> ) }
                </tr>
            </thead>
            <tbody>
                { this.props.data.map(
                    (line: Array<any>, i) => {
                        return <tr key={"table-line-" + i}>
                            {
                                line.map((value, j) => {
                                    return <td key={"table-line-" + i + "-col-" + j}>{value}</td>;
                                })
                            }
                        </tr>;
                    }
                )
                }
            </tbody>

        </table>;
    }
}
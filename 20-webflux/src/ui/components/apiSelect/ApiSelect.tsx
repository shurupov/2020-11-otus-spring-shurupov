import React, {FormEvent} from "react";
import {FormControl, InputLabel, MenuItem, Select} from "@material-ui/core";

interface ApiSelectProps {
    uriPrefix: string;
    updatePrefix: Function;
}

export default class ApiSelect extends React.Component<ApiSelectProps, any>{

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event: FormEvent<any>) {
        const element = event.target as HTMLFormElement;
        this.props.updatePrefix(element.value);
    }

    render () {
        return (
            <FormControl variant="outlined">
                <Select
                    value={this.props.uriPrefix}
                    onChange={this.handleChange}
                    style={{ color: "white" }}
                >
                    <MenuItem value={"/api/v1"}>Controller API</MenuItem>
                    <MenuItem value={"/api/v2"}>Router Function API</MenuItem>
                </Select>
            </FormControl>
        );
    }
}
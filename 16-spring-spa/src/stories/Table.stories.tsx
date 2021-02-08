import {Meta, Story} from "@storybook/react/types-6-0";
import React from "react";
import {Table, TableProps} from "../ui/components/Table";

export default {
    title: 'Table',
    component: Table,
    argTypes: {
        header: {
            header1: 'string'
        }
    },
} as Meta;

const Template: Story<TableProps> = (args) => <Table {...args} />;

export const Table1 = Template.bind({});
Table1.args = {
    header: ["Title1", "Title2", "Title3"],
    data: [
        ["Value11","Value12","Value13",],
        ["Value21","Value22","Value23",],
    ]
};
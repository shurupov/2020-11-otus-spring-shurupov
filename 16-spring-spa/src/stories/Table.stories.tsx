import {Meta, Story} from "@storybook/react/types-6-0";
import React from "react";
import {ItemsTable, TableProps} from "../ui/components/ItemsTable";

export default {
    title: 'Table',
    component: ItemsTable,
    argTypes: {
    },
} as Meta;

const Template: Story<TableProps> = (args) => <ItemsTable {...args} />;

export const Table1 = Template.bind({});
Table1.args = {
    data: [
        { id: "value11", col2: "value12", col3: "value13", col4: "value14",  },
        { id: "value21", col2: "value22", col3: "value23", col4: "value24",  },
    ]
};
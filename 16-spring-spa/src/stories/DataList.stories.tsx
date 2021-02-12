import {Meta, Story} from "@storybook/react/types-6-0";
import React from "react";
import {DataList, DataListProps} from "../ui/components/common/DataList";

export default {
    title: 'Table',
    component: DataList,
    argTypes: {
    },
} as Meta;

const Template: Story<DataListProps> = (args) => <DataList {...args} />;

export const TableWithoutHeaders = Template.bind({});
TableWithoutHeaders.args = {
    title: "Table without Headers",
    columns: ["id", "name", "itemDescription"],
    data: [
        { id: 1, name: "Name1", itemDescription: "Description of the first element"  },
        { id: 2, name: "Name1", itemDescription: "Description of the second element"  },
    ]
};

export const TableWithHeaders = Template.bind({});
TableWithHeaders.args = {
    title: "Table with Headers",
    columns: ["id", "name", "itemDescription"],
    headers: {id: "#", name: "Name",itemDescription:"Description"},
    data: [
        { id: 1, name: "Name1", itemDescription: "Description of the first element"  },
        { id: 2, name: "Name1", itemDescription: "Description of the second element"  },
    ]
};
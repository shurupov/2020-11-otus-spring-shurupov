import {Meta, Story} from "@storybook/react/types-6-0";
import React from "react";
import {BrowserRouter} from "react-router-dom";
import Crumbs from "../../ui/components/common/Crumbs";

export default {
    title: 'Crumbs',
    component: Crumbs,
    argTypes: {
    },
} as Meta;

const Template: Story<{}> = (args) => (
    <BrowserRouter>
        <Crumbs {...args} />
    </BrowserRouter>
);

export const CrumbsExample = Template.bind({});
CrumbsExample.args = {
    items: [
        { caption: "Home", url: "/" },
        { caption: "Second item", url: "/second" },
        { caption: "Third item", url: "/second/third" },
    ]
};
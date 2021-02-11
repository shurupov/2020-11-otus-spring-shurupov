import {Meta, Story} from "@storybook/react/types-6-0";
import React from "react";
import Summary, {SummaryProps} from "../ui/components/Summary";
import {BrowserRouter} from "react-router-dom";

export default {
    title: 'Summary',
    component: Summary,
    argTypes: {
    },
} as Meta;

const Template: Story<SummaryProps> = (args) => (
    <BrowserRouter>
        <Summary {...args} />
    </BrowserRouter>
);

export const SummaryExample = Template.bind({});
SummaryExample.args = {
    counts: {
        books: 5,
        comments: 4,
        authors: 6,
        genres: 8
    }
};
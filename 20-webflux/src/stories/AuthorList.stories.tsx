import {Meta, Story} from "@storybook/react/types-6-0";
import React from "react";
import {BrowserRouter} from "react-router-dom";
import AuthorList, {AuthorListProps} from "../ui/components/author/AuthorList";

export default {
    title: 'AuthorList',
    component: AuthorList,
    argTypes: {
    },
} as Meta;

const Template: Story<AuthorListProps> = (args) => (
    <BrowserRouter>
        <AuthorList {...args} />
    </BrowserRouter>
);

export const AuthorListExample = Template.bind({});
AuthorListExample.args = {
    authors: [
        {
            id: 1,
            firstName: 'Alexander',
            lastName: 'Pushkin',
        },
        {
            id: 2,
            firstName: 'Fedor',
            lastName: 'Dostoevsky',
        }
    ]
};
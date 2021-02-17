import {Meta, Story} from "@storybook/react/types-6-0";
import React from "react";
import BookEdit, {BookEditProps} from "../ui/components/book/BookEdit";

export default {
    title: 'BookEdit',
    component: BookEdit,
    argTypes: {
    },
} as Meta;

const Template: Story<BookEditProps> = (args) => <BookEdit {...args} />;

export const BookEditExample = Template.bind({});
BookEditExample.args = {
    defaultState: {
        name: "Some Book Name",
        authorId: 20,
        genres: [],
    },
    genres: [
        {id: 1, title: "Horror"},
        {id: 2, title: "Drama"},
        {id: 3, title: "Comedy"},
    ],
    authors: [
        {id: 1, name: "Alexander Pushkin"},
        {id: 2, name: "Fedor Dostoevsky"},
        {id: 3, name: "Haruki Murakami"},
    ]
};
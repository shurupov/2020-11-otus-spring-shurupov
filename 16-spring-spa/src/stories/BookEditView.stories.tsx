import {Meta, Story} from "@storybook/react/types-6-0";
import React from "react";
import BookEditView, {BookEditProps} from "../ui/components/book/BookEditView";

export default {
    title: 'BookEditView',
    component: BookEditView,
    argTypes: {
    },
} as Meta;

const Template: Story<BookEditProps> = (args) => <BookEditView {...args} />;

export const BookEditExample = Template.bind({});
BookEditExample.args = {
    book: {
        name: "Some Book Name",
        authorId: 20,
        genres: [],
    },
    genres: [
        {id: 1, name: "Horror"},
        {id: 2, name: "Drama"},
        {id: 3, name: "Comedy"},
    ],
    authors: [
        {id: 1, name: "Alexander Pushkin"},
        {id: 2, name: "Fedor Dostoevsky"},
        {id: 3, name: "Haruki Murakami"},
    ]
};
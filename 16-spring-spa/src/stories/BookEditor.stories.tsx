import {Meta, Story} from "@storybook/react/types-6-0";
import React from "react";
import BookEditor, {BookEditorProps} from "../ui/components/book/BookEditor";

export default {
    title: 'BookEditView',
    component: BookEditor,
    argTypes: {
    },
} as Meta;

const Template: Story<BookEditorProps> = (args) => <BookEditor {...args} />;

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
        {id: 1, firstName: "Alexander", lastName: "Pushkin"},
        {id: 2, firstName: "Fedor", lastName: "Dostoevsky"},
        {id: 3, firstName: "Haruki", lastName: "Murakami"},
    ]
};
import {Meta, Story} from "@storybook/react/types-6-0";
import React from "react";
import BookList, {BookListProps} from "../ui/components/book/BookList";

export default {
    title: 'BookList',
    component: BookList,
    argTypes: {
    },
} as Meta;

const Template: Story<BookListProps> = (args) => <BookList {...args} />;

export const BookListExample = Template.bind({});
BookListExample.args = {
    books: [
        {
            id: 1,
            name: 'The Tale about Fisherman and a Gold Fish',
            genres: "Fairy Tale, Drama",
            author: "Alexander Pushkin"
        },
        {
            id: 2,
            name: 'Player',
            genres: "Drama",
            author: "Fedor Dostoyevsky"
        }
    ]
};
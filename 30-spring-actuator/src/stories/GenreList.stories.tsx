import {Meta, Story} from "@storybook/react/types-6-0";
import React from "react";
import {BrowserRouter} from "react-router-dom";
import GenreList, {GenreListProps} from "../ui/components/genre/GenreList";

export default {
    title: 'GenreList',
    component: GenreList,
    argTypes: {
    },
} as Meta;

const Template: Story<GenreListProps> = (args) => (
    <BrowserRouter>
        <GenreList {...args} />
    </BrowserRouter>
);

export const GenreListExample = Template.bind({});
GenreListExample.args = {
    genres: [
        {
            id: 1,
            name: 'Horror',
        },
        {
            id: 2,
            name: 'Drama',
        }
    ]
};
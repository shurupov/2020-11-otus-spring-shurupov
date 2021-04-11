import {Meta, Story} from "@storybook/react/types-6-0";
import React from "react";
import NavigationPanel from "../../ui/components/common/NavigationPanel";
import {BrowserRouter} from "react-router-dom";

export default {
    title: 'NavigationPanel',
    component: NavigationPanel,
    argTypes: {
    },
} as Meta;

const Template: Story<{}> = (args) => (
    <BrowserRouter>
        <NavigationPanel {...args} />
    </BrowserRouter>
);

export const NavigationPanelExample = Template.bind({});
NavigationPanelExample.args = {
};
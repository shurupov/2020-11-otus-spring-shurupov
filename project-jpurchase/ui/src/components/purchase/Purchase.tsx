import React from "react";
import Title from "antd/lib/typography/Title";
import Paragraph from "antd/lib/typography/Paragraph";
import {ConnectedProductList} from "../productList/ConnectedProductList";

export interface PurchaseProps {
    id: number;
    name: string;
    description: string;
    createdAt: string;
}

export const Purchase = (props: PurchaseProps) => {
    return <>
        <Title>{props.name}</Title>
        <Paragraph>{props.description}</Paragraph>
        <ConnectedProductList />
    </>;
}
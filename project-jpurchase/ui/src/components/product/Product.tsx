import Title from "antd/lib/typography/Title";
import Paragraph from "antd/lib/typography/Paragraph";
import React from "react";
import {List, Select} from "antd";

const { Option } = Select;

export interface ProductProps {
    id: number;
    name: string;
    price: string | number;
    description: string;
    createdAt: string;
    optionName: string;
    options: Array<string>;
    properties: any;
}

export const Product = (props: ProductProps) => {
    return <>
        <Title>{props.name}</Title>
        <Title level={3}>Price: {props.price}</Title>
        <Paragraph>{props.description}</Paragraph>
        {
            props.options.length == 0
                ? null
                : <Paragraph>
                    {props.optionName}&nbsp;
                    <Select value={props.options[0]} style={{ width: 200 }}>
                        {props.options.map(value => <Option key={value} value={value}>{value}</Option>)}
                    </Select>
                </Paragraph>
        }
        <List
            header={<div>Properties</div>}
            bordered
            dataSource={Object.keys(props.properties).map((key) => ({key, value: props.properties[key]}))}
            renderItem={item => (
                <List.Item>
                    {item.key}: {item.value}
                </List.Item>
            )}
        />
    </>;
}
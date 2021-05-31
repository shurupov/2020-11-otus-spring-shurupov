import {Card, Col, Row} from "antd";
import {Link} from "react-router-dom";
import React from "react";
import {ProductProps} from "../product/Product";

export interface ProductsProps {
    purchaseId: number | string;
    products: Array<ProductProps>;
}

export const ProductList = (props: ProductsProps) => {
    const cols = [];
    for (let i = 0; i < props.products.length; i++) {
        cols.push(
            <Col span={8} key={i}>
                <Card title={props.products[i].name} bordered={true} extra={<Link to={`/purchases/${props.purchaseId}/products/${props.products[i].id}`}>Details</Link>}>
                    {props.products[i].description}
                </Card>
            </Col>
        );
    }
    return <Row gutter={[16, 24]}>{cols}</Row>;
}
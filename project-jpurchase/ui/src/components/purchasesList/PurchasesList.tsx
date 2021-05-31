import React from "react";
import {Card, Col, Row} from "antd";
import { Link } from "react-router-dom";
import {PurchaseProps} from "../purchase/Purchase";

interface PurchasesListProps {
    purchasesList: Array<PurchaseProps>;
}

export const PurchasesList = (props: PurchasesListProps) => {
    const cols = [];
    for (let j = 0; j < props.purchasesList.length; j++) {
        cols.push(
            <Col span={8} key={j}>
                <Card title={props.purchasesList[j].name} bordered={true} extra={<Link to={`/purchases/${props.purchasesList[j].id}`}>More</Link>}>
                    {props.purchasesList[j].description}
                </Card>
            </Col>
        );
    }
    return <Row gutter={[16, 24]}>{cols}</Row>;
}
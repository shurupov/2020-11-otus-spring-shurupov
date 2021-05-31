import {Breadcrumb} from "antd";
import React from "react";
import {Link, Route} from "react-router-dom";

export interface BreadCrumbsProps {
    purchaseName?: string;
    purchaseId?: string | number;
    productName?: string;
    productId?: string | number;
}

export const Breadcrumbs = (props: BreadCrumbsProps) => (
    <Breadcrumb style={{ margin: '16px 0' }}>
        <Route path="/">
            <Breadcrumb.Item><Link to={"/"}>Home</Link></Breadcrumb.Item>
        </Route>

        <Route path="/purchases">
            <Breadcrumb.Item><Link to={"/purchases"}>Purchases</Link></Breadcrumb.Item>
        </Route>
        <Route path="/purchases/:id">
            <Breadcrumb.Item><Link to={`/purchases/${props.purchaseId}`}>{props.purchaseName}</Link></Breadcrumb.Item>
        </Route>
        <Route path="/purchases/:purchaseId/products/:productId">
            <Breadcrumb.Item><Link to={`/purchases/${props.purchaseId}/products/${props.productId}`}>{props.productName}</Link></Breadcrumb.Item>
        </Route>

        <Route path="/auth">
            <Breadcrumb.Item><Link to={"/auth"}>Authenticate</Link></Breadcrumb.Item>
        </Route>
        <Route path="/logout">
            <Breadcrumb.Item><Link to={"/logout"}>Logout</Link></Breadcrumb.Item>
        </Route>
    </Breadcrumb>
);
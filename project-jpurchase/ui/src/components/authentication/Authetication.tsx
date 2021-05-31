import {Button, Checkbox, Form, Input} from "antd";
import React from "react";

export interface AuthRequest {
    username: string;
    password: string;
}

interface AuthProps {
    submit: (values: AuthRequest) => void;
}

export const Authentication = (props: AuthProps) => {
    const layout = {
        labelCol: { span: 8 },
        wrapperCol: { span: 16 },
    };

    const tailLayout = {
        wrapperCol: { offset: 8, span: 16 },
    };

    const onFinish = (values: any) => {
        console.log('Success:', values);
    };

    const onFinishFailed = (errorInfo: any) => {
        //console.log('Failed:', errorInfo);
    };

    return (
        <Form
            {...layout}
            name="basic"
            // initialValues={{remember: true}}
            onFinish={props.submit}
            onFinishFailed={onFinishFailed}
            style={{
                paddingTop: 20,
                maxWidth: 600,
                margin: "auto"
            }}
            autoComplete="off"
        >
            <Form.Item
                label="Username"
                name="username"
                rules={[{required: true, message: 'Please input your username!'}]}
            >
                <Input autoComplete="off"/>
            </Form.Item>

            <Form.Item
                label="Password"
                name="password"
                rules={[{required: true, message: 'Please input your password!'}]}
            >
                <Input.Password autoComplete="off"/>
            </Form.Item>

            {/*<Form.Item {...tailLayout} name="remember" valuePropName="checked">
                <Checkbox>Remember me</Checkbox>
            </Form.Item>*/}

            <Form.Item {...tailLayout}>
                <Button type="primary" htmlType="submit">
                    Submit
                </Button>
            </Form.Item>
        </Form>
    );
};
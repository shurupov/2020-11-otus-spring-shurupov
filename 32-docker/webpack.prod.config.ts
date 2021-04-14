// @ts-ignore
import path from "path";
// @ts-ignore
import webpack from "webpack";
// @ts-ignore
import HtmlWebpackPlugin from "html-webpack-plugin";
// @ts-ignore
import ForkTsCheckerWebpackPlugin from "fork-ts-checker-webpack-plugin";
//import ESLintPlugin from "eslint-webpack-plugin";
import { CleanWebpackPlugin } from "clean-webpack-plugin";

const config: webpack.Configuration = {
    mode: "production",
    entry: "./src/ui/index.tsx",
    output: {
        path: path.resolve(__dirname, "build/"),
        filename: "[name].[contenthash].js",
        publicPath: "/",
    },
    module: {
        rules: [
            {
                test: /\.(ts|js)x?$/i,
                exclude: /node_modules/,
                use: {
                    loader: "babel-loader",
                    options: {
                        presets: [
                            "@babel/preset-env",
                            "@babel/preset-react",
                            "@babel/preset-typescript",
                        ],
                    },
                },
            },
            {
                test: /\.css$/i,
                use: ["style-loader", "css-loader"],
            },
            {
                test: /\.(png|jpe?g|gif|svg|eot|ttf|woff|woff2)$/i,
                // More information here https://webpack.js.org/guides/asset-modules/
                type: "asset",
            },
        ],
    },
    resolve: {
        extensions: [".tsx", ".ts", ".js"],
        alias: {
            components: path.resolve(__dirname, "src/ui/components"),
            store: path.resolve(__dirname, "src/ui/store"),
            smart: path.resolve(__dirname, "src/ui/smart"),
        },
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: "src/ui/index.html",
        }),
        new ForkTsCheckerWebpackPlugin({
            async: false,
        }),
        /*new ESLintPlugin({
            extensions: ["js", "jsx", "ts", "tsx"],
        }),*/
        new CleanWebpackPlugin(),
    ],
};

export default config;
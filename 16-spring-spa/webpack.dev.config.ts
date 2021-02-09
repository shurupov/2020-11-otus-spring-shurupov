// @ts-ignore
import path from "path";
// @ts-ignore
import webpack from "webpack";
// @ts-ignore
import HtmlWebpackPlugin from "html-webpack-plugin";
// @ts-ignore
import ForkTsCheckerWebpackPlugin from 'fork-ts-checker-webpack-plugin';

import ESLintPlugin from "eslint-webpack-plugin";

const config: webpack.Configuration = {
    mode: "development",
    output: {
        publicPath: "/",
    },
    entry: "./src/ui/index.tsx",
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
                // For pure CSS - /\.css$/i,
                // For Sass/SCSS - /\.((c|sa|sc)ss)$/i,
                // For Less - /\.((c|le)ss)$/i,
                test: /\.((c|sa|sc)ss)$/i,
                use: [
                    "style-loader",
                    {
                        loader: "css-loader",
                        /*options: {
                            // Run `postcss-loader` on each CSS `@import`, do not forget that `sass-loader` compile non CSS `@import`'s into a single file
                            // If you need run `sass-loader` and `postcss-loader` on each CSS `@import` please set it to `2`
                            importLoaders: 1,
                        },*/
                    },
                    // {
                    //     loader: "postcss-loader",
                    //     options: { plugins: () => [postcssPresetEnv({ stage: 0 })] },
                    // },
                    // Can be `less-loader`
                    // {
                    //     loader: "sass-loader",
                    // },
                ],
            },
            // For webpack v5
            {
                test: /\.(png|jpe?g|gif|svg|eot|ttf|woff|woff2)$/i,
                // More information here https://webpack.js.org/guides/asset-modules/
                type: "asset",
            },
        ],
    },
    resolve: {
        extensions: [".tsx", ".ts", ".js"],
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: "src/ui/index.html",
        }),
        new webpack.HotModuleReplacementPlugin(),
        new ForkTsCheckerWebpackPlugin({
            async: false
        }),
        new ESLintPlugin({
            extensions: ["js", "jsx", "ts", "tsx"],
        }),
    ],
    devtool: "inline-source-map",
    devServer: {
        contentBase: path.join(__dirname, "build"),
        historyApiFallback: true,
        port: 4000,
        open: true,
        hot: true,
        before: (app) => {
            app.get('/api/books', (req, res) => res.send([
                {
                    id: '1',
                    name: 'The Tale about Fisherman and a Gold Fish',
                    genres: "Fairy Tale, Drama",
                    author: "Alexander Pushkin"
                },
                {
                    id: '2',
                    name: 'Player',
                    genres: "Drama",
                    author: "Fedor Dostoyevsky"
                }
            ]));
        }
    },
};

export default config;

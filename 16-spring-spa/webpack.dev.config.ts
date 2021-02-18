// @ts-ignore
import path from "path";
// @ts-ignore
import webpack from "webpack";
// @ts-ignore
import HtmlWebpackPlugin from "html-webpack-plugin";
// @ts-ignore
import ForkTsCheckerWebpackPlugin from 'fork-ts-checker-webpack-plugin';

//import ESLintPlugin from "eslint-webpack-plugin";

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
                    },
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
        new webpack.HotModuleReplacementPlugin(),
        new ForkTsCheckerWebpackPlugin({
            async: false
        }),
        /*new ESLintPlugin({
            extensions: ["js", "jsx", "ts", "tsx"],
        }),*/
    ],
    devtool: "inline-source-map",
    devServer: {
        contentBase: path.join(__dirname, "build"),
        historyApiFallback: true,
        port: 4000,
        open: true,
        hot: true,
        before: (app) => {
            app.get('/api/genres', (req, res) => res.send([
                {
                    id: 1,
                    name: 'Horror',
                },
                {
                    id: 2,
                    name: 'Drama',
                }
            ]));
            app.get('/api/authors', (req, res) => res.send([
                {
                    id: 1,
                    firstName: 'Alexander',
                    lastName: 'Pushkin',
                    name: 'Alexander Pushkin',
                },
                {
                    id: 2,
                    name: 'Fedor Dostoevsky',
                    firstName: 'Fedor',
                    lastName: 'Dostoevsky',
                }
            ]));
            app.get('/api/books', (req, res) => res.send([
                {
                    id: 1,
                    name: 'The Tale about Fisherman and a Gold Fish',
                    genres: "Fairy Tale, Drama",
                    author: "Alexander Pushkin"
                },
                {
                    id: 2,
                    name: 'Player',
                    genres: "Drama",
                    author: "Fedor Dostoyevsky"
                }
            ]));
            app.get('/api/books/1', (req, res) => res.send({
                id: '1',
                name: 'The Tale about Fisherman and a Gold Fish',
                genres: [1,2,3],
                authorId: 1
            }));
        }
    },
};

export default config;

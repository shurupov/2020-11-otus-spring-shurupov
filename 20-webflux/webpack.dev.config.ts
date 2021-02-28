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
            app.get('/api/v1/authors', (req, res) => res.send([
                {
                    id: "603800398f4d9c4d6496089d",
                    firstName: 'Alexander',
                    lastName: 'Pushkin',
                },
                {
                    id: "603800398f4d9c4d64960911",
                    lastName: 'Dostoevsky',
                    firstName: 'Fedor',
                }
            ]));
            app.get('/api/v1/books', (req, res) => res.send([
                {
                    id: "603800398f4d9c4d64961055",
                    name: 'The Tale about Fisherman and a Gold Fish',
                    genres: ["Fairy Tale", "Drama"],
                    author: {
                        id: "603800398f4d9c4d6496089d",
                        firstName: 'Alexander',
                        lastName: 'Pushkin',
                    }
                },
                {
                    id: "603800398f4d9c4d64961364",
                    name: 'Player',
                    genres: ["Drama"],
                    author: {
                        id: "603800398f4d9c4d64960911",
                        lastName: 'Dostoevsky',
                        firstName: 'Fedor',
                    }
                }
            ]));
            app.get('/api/v1/books/603800398f4d9c4d64961055', (req, res) => res.send({
                id: "603800398f4d9c4d64961055",
                name: 'The Tale about Fisherman and a Gold Fish',
                authorId: "603800398f4d9c4d6496089d",
                genres: ["Fairy Tale", "Drama"],
                comments: ["Interesting tale", "Cool!"],
            }));
        }
    },
};

export default config;

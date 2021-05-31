FROM node:10.24.1-alpine3.11
ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR

ADD ./ $PROJECT_DIR/
RUN npm install
RUN npm run build

FROM nginx:1.17.1-alpine
ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR
ADD default.conf /etc/nginx/conf.d/
COPY --from=0 $PROJECT_DIR/build/* /usr/share/nginx/html/
EXPOSE 80
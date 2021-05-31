FROM maven:3.6.3-openjdk-11
ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR

ADD ./ $PROJECT_DIR/
RUN mvn package -DskipTests

FROM openjdk:11-jre-slim
ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR
COPY --from=0 $PROJECT_DIR/target/jpurchase-gateway-1.0.jar $PROJECT_DIR/
EXPOSE 8003
CMD ["java", "-jar", "/opt/project/jpurchase-gateway-1.0.jar"]
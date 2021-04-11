FROM maven:3.6.3-jdk-11
ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR

#Will be removed after separation backend and frontend
ADD ./* $PROJECT_DIR/
#ADD ./pom.xml $PROJECT_DIR
#RUN mvn -B clean install -DskipTests -Dcheckstyle.skip -Dasciidoctor.skip
#RUN mvn dependency:resolve
#ADD ./src/ $PROJECT_DIR/src
#RUN mvn -X package

RUN mvn dependency:resolve
ADD ./src/ $PROJECT_DIR/src
RUN mvn install -DskipTests

FROM openjdk:11-jdk
ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR
COPY --from=0 $PROJECT_DIR/target/32-docker* $PROJECT_DIR/
EXPOSE 8080
RUN ls
CMD ["java", "-jar", "/opt/project/32-docker-1.0.jar"]
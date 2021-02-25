# Spring REST SPA

## Task
Implement CRUD Shell application of book library with entities: Book, BookComment, Author, Genre. 
Use Spring, REST and SPA.

## Used
- Backend
    - Spring boot
    - Annotation-based configuration
    - Lombok
    - YML application configuration
    - Postgresql database for production
    - H2 Embedded database for tests
    - JPA
    - Spring Data JPA
    - Liquibase
    - JUnit 5
    - Mockito
    - AssertJ
    - Hamcrest
    - @WebMvcTest annotation in tests
- Frontend
    - npm, webpack, babel
    - TypeScript
    - React.js
    - Redux
    - @reduxjs/toolkit
    - redux-saga
    - Redux Router
    - connected-react-router
    - material-ui
    - Storybook


## Command to init mongo container image and start it (You need to have docker installed)
`docker run -d -p 127.0.0.1:27017:27017 --name mongo mongo`

## Command to stop mongo container
`docker stop mongo`

## Command to start mongo (You need to have mongo container initialized)
`docker start mongo`

## Command to remove mongo container image
`docker rm mongo`

## Command to build
`mvn clean package`

## Command to start the application
`java -jar target/16-spring-spa-1.0.jar`

## Command to build and start
`mvn spring-boot:run`

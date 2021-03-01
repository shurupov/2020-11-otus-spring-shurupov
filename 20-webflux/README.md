# Spring ЦуиАдгч

## Task
Implement CRUD Shell application of book library with entities: Book, BookComment, Author, Genre. 
Use Spring Reactive Stack (Webflux).

## Used
- Backend
    - Spring boot
    - Annotation-based configuration
    - Lombok
    - YML application configuration
    - MongoDB database for production
    - Embedded MongoDB for tests
    - Reactive Spring Data MongoDB
    - Mongock
    - JUnit 5
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
`java -jar target/20-webflux-1.0.jar`

## Command to build and start
`mvn spring-boot:run`

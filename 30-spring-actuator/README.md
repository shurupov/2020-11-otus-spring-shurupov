# Spring Security Authorization

## Task
Implement CRUD Shell application of book library with entities: Book, BookComment, Author, Genre. 
Use Spring, REST and SPA. Use Spring Security. Separate access to urls and service methods by user roles. 

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
    - Spring Security
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
    

## Command to init postgres container image and start it (You need to have docker installed)
`docker run -d --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=springpassword -e POSTGRES_USER=springuser -e POSTGRES_DB=library postgres`

## Command to stop postgres container
`docker stop postgres`

## Command to start postgres (You need to have postgres container initialized)
`docker start postgres`

## Command to remove postgres container image
`docker rm postgres`

## Command to build
`mvn clean package`

## Command to start the application
`java -jar target/16-spring-spa-1.0.jar`

## Command to build and start
`mvn spring-boot:run`

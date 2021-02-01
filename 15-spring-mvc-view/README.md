# Spring MVC View

## Task
Implement CRUD Shell application of book library with entities: Book, BookComment, Author, Genre. 
Use Spring Data JPA.

## Used
- Spring boot
- Annotation-based configuration
- Lombok
- YML application configuration
- Thymeleaf
- Postgresql database for production
- H2 Embedded database for tests
- JPA
- Spring Data JPA
- Liquibase
- JUnit 5
- Mockito
- AssertJ
- @DataJpaTest, @DirtiesContext annotation in tests

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
`java -jar target/15-spring-mvc-view-1.0.jar`

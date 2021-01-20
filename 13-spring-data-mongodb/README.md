# Spring Data JPA

## Task
Implement CRUD Shell application of book library with entities: Book, BookComment, Author, Genre. 
Use Spring Data JPA.

## Used
- Spring boot
- Annotation-based configuration
- Lombok
- YML application configuration
- Spring Shell
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
`docker run -d -p 127.0.0.1:27017:27017 --name mongo mongo`

## Command to stop postgres container
`docker stop postgres`

## Command to start postgres (You need to have postgres container initialized)
`docker start postgres`

## Command to remove postgres container image
`docker rm postgres`

## Command to build
`mvn clean package`

## Command to start the application
`java -jar target/11-spring-data-jpa-1.0.jar`

# DAO on Spring JDBC 

## Task
Implement CRUD Shell application of book library with entities: Book, Author, Genre.

## Used
- Spring boot
- Annotation-based configuration
- Lombok
- YML application configuration
- Spring Shell
- Postgresql database for production
- H2 Embedded database for tests
- Spring JDBC
- Liquibase
- JUnit 5
- Mockito
- AssertJ
- @JdbcTest, @DirtiesContext annotation in tests

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
`java -jar target/07-spring-jdbc-0.0.1-SNAPSHOT.jar`

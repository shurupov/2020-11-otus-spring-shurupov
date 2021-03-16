# Spring Batch

## Task
Implement Spring Batch Application that converts data from one database (MongoDB) to another (Postgres).

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
    - MongoDB database for production
    - Embedded MongoDB for tests
    - Spring Data MongoDB
    - Mongock
    - Spring Shell
    - Spring Batch
    - JUnit 5
    - Mockito
    - AssertJ
    - Hamcrest

<details>
  <summary>Postgres container (how to create, start, stop, remove)</summary>

  ## Command to init postgres container image and start it (You need to have docker installed)
  `docker run -d --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=springpassword -e POSTGRES_USER=springuser -e POSTGRES_DB=library postgres`
  
  ## Command to stop postgres container
  `docker stop postgres`
  
  ## Command to start postgres (You need to have postgres container initialized)
  `docker start postgres`
  
  ## Command to remove postgres container image
  `docker rm postgres`

</details>

<details>
  <summary>Mongo container (how to create, start, stop, remove)</summary>

  ## Command to init mongo container image and start it (You need to have docker installed)
  `docker run -d -p 127.0.0.1:27017:27017 --name mongo mongo`
  
  ## Command to stop mongo container
  `docker stop mongo`
  
  ## Command to start mongo (You need to have mongo container initialized)
  `docker start mongo`
  
  ## Command to remove mongo container image
  `docker rm mongo`
</details>

<details>
  <summary>Application (how to build, start)</summary>

  ## Command to build
  `mvn clean package`
  
  ## Command to start the application
  `java -jar target/16-spring-spa-1.0.jar`
  
  ## Command to build and start
  `mvn spring-boot:run`
</details>


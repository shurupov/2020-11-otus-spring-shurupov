# Spring Boot Actuator, Spring Data REST, HATEOAS, HAL-Explorer, Prometheus

## Task
Add Actuator to microservice. Implement CRUD interface using Spring Data REST. Use HATEOAS Priciple. Use HAL-Explorer. 

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
    - Spring Boot Actuator
    - Spring Data REST
    - HAL-Explorer
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
    
<details>
  <summary>Database (how to build, start, stop, remove)</summary>

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
  <summary>Application (how to build, start)</summary>

## Command to build
`mvn clean package`
  
## Command to start the application
`java -jar target/30-spring-actuator-1.0.jar`

## Command to build and start
`mvn spring-boot:run`

[Application UI](http://localhost:8080)

[HAL-Explorer](http://localhost:8080/api/v2)

</details>

## Start Prometheus
`docker run -p 9090:9090 -v ./prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus`
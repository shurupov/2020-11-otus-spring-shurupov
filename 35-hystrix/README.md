# Docker, Docker Compose, Testcontainers

## Task
Pack application to docker containers. Start application using docker-compose, stacks, kubernetes.
Implement integration tests that use real database. 

## Used
- Docker
- Docker compose
- Testcontainers
    
<details>
  <summary>Launch tests (including integration on real db)</summary>

## Command to launch tests
`mvn clean test`

</details>

<details>
  <summary>Start application using Docker Compose</summary>

## Command to start.
The first launch will also build about 10 seconds

`docker-compose up` for linux and `docker compose up` for windows

</details>
    
<details>
  <summary>Build docker-images separately</summary>

## Command to build backend docker-image
`docker build -t 35-hystrix-backend:v2 ./backend`

## Command to build frontend docker-image
`docker build -t 32-hystrix-frontend:v2 ./frontend`

</details>

[Application UI](http://localhost)

Available users:

| username | password |
| -------- | ------- |
| user1 | !user1 |
| admin | !admin |

[HAL-Explorer](http://localhost/api/v2)

##Hystrix

To check whether hystrix is working good after some clicks on UI stop postgres.

## Command to stop postgres
`docker-compose stop postgresdb`

## Command to start postgres again
`docker-compose start postgresdb`


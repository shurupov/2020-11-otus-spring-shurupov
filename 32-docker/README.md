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
  <summary>Build docker-images (you need this one time)</summary>

## Command to build backend docker-image
`docker build -t 32-docker-backend:v1 ./backend`

## Command to build frontend docker-image
`docker build -t 32-docker-frontend:v1 ./frontend`

</details>

<details>
  <summary>Start application using Docker Compose</summary>

## Command to start
`docker-compose up`

## Command to start with images building
`docker-compose --file docker-compose-with-build.yml up`

The first time it takes about 10 minutes to build and to start.

</details>

[Application UI](http://localhost)

Available users:

| username | password |
| -------- | ------- |
| user1 | !user1 |
| admin | !admin |

[HAL-Explorer](http://localhost/api/v2)


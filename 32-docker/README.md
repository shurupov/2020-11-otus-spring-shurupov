# Spring Boot Actuator, Spring Data REST, HATEOAS, HAL-Explorer, Prometheus

## Task
Pack application to docker containers. Start application using docker-compose, stacks, kubernetes. 

## Used
- Docker
- Docker compose
    
<details>
  <summary>Build docker-images (you need this one time)</summary>

## Command to build backend docker-image
`docker build -t 32-docker-backend:v1 ./backend`

## Command to build frontend docker-image
`docker build -t 32-docker-frontend:v1 ./frontend`

</details>

<details>
  <summary>Start application using Docker Compose</summary>

## Command to launch
`docker-compose up`

[Application UI](http://localhost)

[HAL-Explorer](http://localhost/api/v2)

</details>
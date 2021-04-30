# Hystrix

## Task
Cover methods/endpoint with hystrix fallback processing.

## Used
- Hystrix
    
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

[Application UI](http://localhost)

Available users:

| username | password |
| -------- | ------- |
| user1 | !user1 |
| admin | !admin |

## Hystrix

To check whether hystrix is working good after some clicks on UI stop postgres.

## Command to stop postgres
`docker-compose stop postgresdb`

## Command to start postgres again
`docker-compose start postgresdb`


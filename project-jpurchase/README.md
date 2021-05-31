# Spring Framework Developer Course Final Project

Project: Joint Purchase Service

Description: Joint Purchase Organization Automation

[Presentation (in russian)](https://docs.google.com/presentation/d/1C9oSKHsEsWiBH7c4Vl7yk4_P6muZYkbibHyJ4GXSw5o)

## Task
Start to develop microservice joint purchase system. 

## Used
- Zuul Gateway API
- Eureka Service Discovery
- JWT-Token authentication
- Nginx UI container and gateway router
- UI on React.JS
- Docker as containers
- Docker Compose container orchestration

<details>
  <summary>Start application in production mode</summary>

## Command to init/start
`docker-compose up -d`

## Command to stop
`docker-compose down`

</details>

<details>
  <summary>Start application in development mode</summary>

## Command to build backend (in root folder)
`mvn clean package`

## Command to prepare frontend (in `ui` folder)
`npm install && npm run build`

## Command to init/start database and backend (in root folder)
`docker-compose --file docker-compose-dev.yml up -d`

## Command to stop
`docker-compose down`
  
</details>
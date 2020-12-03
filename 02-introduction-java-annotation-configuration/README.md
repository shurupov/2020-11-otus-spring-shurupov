# Spring Context Introduction. Java based and Annotation based configuration

## Task
You need to create an application that asks first name and last name then writes some amount of questions with possible answers and reads numbers of answers in terminal. Then it calculates student's rating. 

## Some implementation details
* Spring context contains three beans
* Spring context is declared in annotation based configuration
* Spring simple application creates context, gets bean from it and requests one method
* Questions, answers, and correct answer numbers are in CSV file in resources
* CSV file name, count of questions to ask, and map scores to rating are in properties file.

To build project to jar use command `maven package`

To start application use command `java -jar target/01-introduction-xml-configuration-1.0-spring-boot.jar`

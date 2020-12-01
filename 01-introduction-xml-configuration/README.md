# Spring Context Introduction. XML Configuration

## Task
You need to create an application that reads questions and answers from an CSV-file in a classpath and outputs them in terminal.

## Some implementation details
* Spring context contains one bean
* Spring context is declared in XML-configuration
* Spring simple application creates context, gets bean from it and requests one method
* Service bean has only one method.
* This method reads data from CSV-file from classpath and maps it to a list of domain objects
* Single bean method is tested by unit-test.
* Project is packaged to JAR and executed by `java -jar` command

To build project to jar use command `maven package`

To start application use command `java -jar target/01-introduction-xml-configuration-1.0-spring-boot.jar`

# Spring Integration

## Task
Implement Spring Integration Application that describes some domain model (need to come up with).

## Implemented
We have a restaurant with some amount of tables, waiter, kitchen with some shops (cold shop, hot shop, drink shop, pastry shop).
Guests from one table do order, waiter tells order items to kitchen. Kitchen breaks down order to the order items.
Every order item contains dish. Order part for this dish directs to custom shop to cook it.
Shop is selected according to dish type.
Every dish type is cooked some time, depends on dish type.
After a dish is cooked it directs to some common area where should be aggregated to an order result for a table it requested.
An order result is collected, it goes back to a table.

## Used
- Backend
    - Spring boot
    - Java-based configuration
    - Lombok
    - Spring Integration

<details>
  <summary>Application (how to build, start)</summary>

  ## Command to build
  `mvn clean package`
  
  ## Command to start the application
  `java -jar target/29-spring-integration-1.0.jar`
  
  ## Command to build and start
  `mvn spring-boot:run`
</details>


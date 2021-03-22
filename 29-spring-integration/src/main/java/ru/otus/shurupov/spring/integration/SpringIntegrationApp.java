package ru.otus.shurupov.spring.integration;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import ru.otus.shurupov.spring.integration.domain.Dish;
import ru.otus.shurupov.spring.integration.domain.FoodItem;
import ru.otus.shurupov.spring.integration.domain.OrderItem;
import ru.otus.shurupov.spring.integration.gateway.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@IntegrationComponentScan
@SpringBootApplication
public class SpringIntegrationApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringIntegrationApp.class);

        Table table = ctx.getBean(Table.class);

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Collection<OrderItem> orderItems = generateOrderItems();
            System.out.println("Ordered " + orderItems);
            Collection<FoodItem> food = table.waiter(orderItems);
            System.out.println("Cooked " + food);
            System.out.println();
        }


    }

    private static Collection<OrderItem> generateOrderItems() {
        int tableNumber = RandomUtils.nextInt(1, 10);
        List<OrderItem> items = new ArrayList<>();
        for ( int i = 0; i < RandomUtils.nextInt( 1, 10 ); ++ i ) {
            items.add( generateOrderItem(tableNumber) );
        }
        return items;
    }

    private static OrderItem generateOrderItem(int tableNumber) {
        return new OrderItem(tableNumber, Dish.values()[ RandomUtils.nextInt( 0, 4 ) ].getName() );
    }
}

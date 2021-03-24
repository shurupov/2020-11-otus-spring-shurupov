package ru.otus.shurupov.spring.integration;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import ru.otus.shurupov.spring.integration.domain.Dish;
import ru.otus.shurupov.spring.integration.domain.OrderItem;
import ru.otus.shurupov.spring.integration.gateway.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@IntegrationComponentScan
@SpringBootApplication
public class SpringIntegrationApp {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringIntegrationApp.class);

        Table table = ctx.getBean(Table.class);
        ForkJoinPool pool = ForkJoinPool.commonPool();

        System.out.println("Restaurant is open.");

        while (true) {
            Thread.sleep(RandomUtils.nextInt(1000, 5000));

            pool.execute( () -> {
                Collection<OrderItem> orderItems = generateOrderItems();
                table.order(orderItems);
                System.out.println();
                System.out.printf(
                        "Ordered %s for table %s.%n ",
                        orderItems.stream()
                                .map(OrderItem::getName)
                                .collect(Collectors.joining(",")),
                        orderItems.stream().findFirst().get().getTable()
                );
                System.out.println();
            });
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
        return new OrderItem(tableNumber, Dish.values()[ RandomUtils.nextInt( 0, Dish.values().length ) ].getName() );
    }
}

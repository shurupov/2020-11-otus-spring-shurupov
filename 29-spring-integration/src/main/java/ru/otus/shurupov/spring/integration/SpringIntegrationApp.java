package ru.otus.shurupov.spring.integration;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import ru.otus.shurupov.spring.integration.domain.FoodItem;
import ru.otus.shurupov.spring.integration.domain.OrderItem;
import ru.otus.shurupov.spring.integration.gateway.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@IntegrationComponentScan
@SpringBootApplication
public class SpringIntegrationApp {

    private static final String[] MENU = { "coffee", "tea", "smoothie", "whiskey", "beer", "cola", "water" };

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringIntegrationApp.class);

        Table table = ctx.getBean(Table.class);

        Collection<FoodItem> food = table.waiter(generateOrderItems());
        System.out.println("SpringIntegrationApp.main");
        food.forEach(System.out::println);
    }

    private static Collection<OrderItem> generateOrderItems() {
        List<OrderItem> items = new ArrayList<>();
        for ( int i = 0; i < RandomUtils.nextInt( 1, 5 ); ++ i ) {
            items.add( generateOrderItem() );
        }
        return items;
    }

    private static OrderItem generateOrderItem() {
        return new OrderItem( MENU[ RandomUtils.nextInt( 0, MENU.length ) ] );
    }
}

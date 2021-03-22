package ru.otus.shurupov.spring.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.shurupov.spring.integration.domain.Dish;
import ru.otus.shurupov.spring.integration.domain.DishType;
import ru.otus.shurupov.spring.integration.domain.FoodItem;
import ru.otus.shurupov.spring.integration.domain.OrderItem;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
public class WaiterConfig {

    @Bean
    public QueueChannel waiterOrder() {
        return MessageChannels.queue().get();
    }

    @Bean
    public QueueChannel waiterFood() {
        return MessageChannels.queue(5).get();
    }

    @Bean IntegrationFlow waiterFoodFlow() {
        return IntegrationFlows.from("waiterFood")
                .handle(m -> {
                    if (m.getPayload() instanceof List) {
                        List<FoodItem> food = ((List<FoodItem>) m.getPayload());
                        System.out.println();
                        System.out.printf("Food %s is ready for table %s.%n ", food.stream().map(FoodItem::getName).collect(Collectors.joining(",")), food.stream().findFirst().get().getTable());
                        System.out.println();
                    }
                }).get();
    }

    @Bean
    public IntegrationFlow waiterFlow() {
        return IntegrationFlows.from("waiterOrder")
                .split()
                .<OrderItem, DishType>route(
                        i -> Objects.requireNonNull(Dish.fromString(i.getName())).getType(),
                        mapping -> mapping
                                .subFlowMapping(DishType.COLD_DISH, sf -> sf.channel("coldShopChannel"))
                                .subFlowMapping(DishType.HOT_DISH, sf -> sf.channel("hotShopChannel"))
                                .subFlowMapping(DishType.HOT_DRINK, sf -> sf.channel("drinksShopChannel"))
                                .subFlowMapping(DishType.COLD_DRINK, sf -> sf.channel("drinksShopChannel"))
                                .subFlowMapping(DishType.DESSERT, sf -> sf.channel("pastryShopChannel"))
                )
                .get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate( 100 ).maxMessagesPerPoll( 2 ).get();
    }
}

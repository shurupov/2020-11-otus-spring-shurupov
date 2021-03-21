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
import ru.otus.shurupov.spring.integration.domain.OrderItem;

import java.util.Objects;

@Configuration
public class WaiterConfig {

    @Bean
    public QueueChannel waiterOrder() {
        return MessageChannels.queue().get();
    }

    @Bean
    public PublishSubscribeChannel waiterFood() {
        return MessageChannels.publishSubscribe().get();
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
                )
                .get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate( 100 ).maxMessagesPerPoll( 2 ).get();
    }
}

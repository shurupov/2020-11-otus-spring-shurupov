package ru.otus.shurupov.spring.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.Message;
import ru.otus.shurupov.spring.integration.domain.FoodItem;

import java.util.*;

@Configuration
public class WaiterFoodCarryingConfig {

    @Bean
    public QueueChannel readyFoodChannel() {
        return MessageChannels.queue().get();
    }

    @Bean
    public IntegrationFlow readyFoodAggregationFlow() {
        return IntegrationFlows.from("readyFoodChannel")
                .aggregate(as -> {
                    as.headersFunction((g) -> {
                        Collection<Message<?>> foodMessages = g.getMessages();
                        Map<String, Object> foods = new HashMap<>();
                        for (Message<?> foodItemMessage : foodMessages) {
                            if (!(foodItemMessage.getPayload() instanceof FoodItem)) {
                                continue;
                            }
                            FoodItem foodItem = (FoodItem) foodItemMessage.getPayload();
                            if (!foods.containsKey("1")) {
                                foods.put("1", new ArrayList());
                            }
                            List<FoodItem> list = (List<FoodItem>) foods.get("1");
                            list.add(foodItem);
                        }
                        return foods;
                    });
                })
                .channel("waiterFood")
                .get();
    }

}

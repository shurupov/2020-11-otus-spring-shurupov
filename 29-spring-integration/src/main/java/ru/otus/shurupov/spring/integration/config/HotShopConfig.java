package ru.otus.shurupov.spring.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;

@Configuration
public class HotShopConfig {

    @Bean
    public QueueChannel hotShopChannel() {
        return MessageChannels.queue().get();
    }

    @Bean
    public IntegrationFlow hotShopFlow() {
        return IntegrationFlows.from("hotShopChannel")
                .handle("hotShop", "cook")
                .channel("readyFoodChannel")
                .get();
    }
}

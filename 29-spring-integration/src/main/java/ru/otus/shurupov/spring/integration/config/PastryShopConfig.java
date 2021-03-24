package ru.otus.shurupov.spring.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;

@Configuration
public class PastryShopConfig {

    @Bean
    public QueueChannel pastryShopChannel() {
        return MessageChannels.queue().get();
    }

    @Bean
    public IntegrationFlow pastryShopFlow() {
        return IntegrationFlows.from("pastryShopChannel")
                .handle("pastryShop", "cook")
                .channel("readyFoodChannel")
                .get();
    }
}

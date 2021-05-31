package ru.otus.shurupov.jointpurchase.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.shurupov.jointpurchase.gateway.filters.JwtAuthenticationFilter;

//@Configuration
public class ZuulFilters {
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
}

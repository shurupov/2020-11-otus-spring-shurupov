package ru.otus.shurupov.jointpurchase.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import ru.otus.shurupov.jointpurchase.domain.Product;
import ru.otus.shurupov.jointpurchase.domain.Purchase;
import ru.otus.shurupov.jointpurchase.domain.User;

@Configuration
public class DataRestConfiguration implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Purchase.class, Product.class, User.class);
    }
}

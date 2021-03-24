package ru.otus.shurupov.spring.integration.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.shurupov.spring.integration.domain.OrderItem;

import java.util.Collection;

@MessagingGateway
public interface Table {
    @Gateway(requestChannel = "waiterOrder")
    void order(Collection<OrderItem> order);
}

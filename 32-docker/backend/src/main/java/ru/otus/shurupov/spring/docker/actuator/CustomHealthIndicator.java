package ru.otus.shurupov.spring.docker.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        return Health.up()
                .status(Status.UP)
                .withDetail("message", "My Custom Health Indicator")
                .withDetail("ok", "everything is ok!")
                .build();
    }
}

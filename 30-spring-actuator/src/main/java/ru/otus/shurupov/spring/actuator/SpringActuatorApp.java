package ru.otus.shurupov.spring.actuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableSwagger2
public class SpringActuatorApp {

    private static final Logger logger = LoggerFactory.getLogger(SpringActuatorApp.class);

    public static void main(String[] args) {
        logger.info("Starting");
        SpringApplication.run(SpringActuatorApp.class);
        logger.info("Started");
    }
}

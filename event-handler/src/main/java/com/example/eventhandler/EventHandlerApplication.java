package com.example.eventhandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.*")
@ComponentScan(basePackages = {"com.example"})
@EntityScan(basePackages = "com.example")
public class EventHandlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventHandlerApplication.class, args);
    }

}

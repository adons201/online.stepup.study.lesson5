package ru.stepup.online;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "ru.stepup.online", lazyInit = true)
@EntityScan(basePackages = "ru.stepup.online.entity")
@EnableJpaRepositories(basePackages = "ru.stepup.online.repo")
@ConfigurationPropertiesScan(basePackages = "ru.stepup.online")
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}



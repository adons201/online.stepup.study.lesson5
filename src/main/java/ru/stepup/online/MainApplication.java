package ru.stepup.online;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Locale;

@SpringBootApplication
@ComponentScan(basePackages = "ru.stepup.online", lazyInit = true)
@EntityScan(basePackages = "ru.stepup.online.entity")
@EnableJpaRepositories(basePackages = "ru.stepup.online.repo")
public class MainApplication {

    public static void main(String[] args) {
        Locale.setDefault(new Locale("ru"));
        SpringApplication.run(MainApplication.class, args);
    }
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

}



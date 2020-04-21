package com.example.demo.repository;

import com.example.demo.model.AbstractTask;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(AbstractTaskRepository repository) {
        return args -> {

            log.info("Preloading " + repository.save(new AbstractTask("Question1", 3)));
            log.info("Preloading " + repository.save(new AbstractTask("Question2", 4)));

        };
    }

}

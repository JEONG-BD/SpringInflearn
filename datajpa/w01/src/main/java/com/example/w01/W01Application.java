package com.example.w01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
@EnableJpaAuditing
public class W01Application {

    public static void main(String[] args) {
        SpringApplication.run(W01Application.class, args);
    }

    @Bean
    public AuditorAware<String> autitorPriovider(){
        return () -> Optional.of(UUID.randomUUID().toString());
    }
}

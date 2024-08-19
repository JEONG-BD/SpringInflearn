package com.example.w01;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class W01Application {

    public static void main(String[] args) {
        SpringApplication.run(W01Application.class, args);
    }

    @Bean
    Hibernate5JakartaModule hibernate5Module(){
        Hibernate5JakartaModule hibernate5JakartaModule = new Hibernate5JakartaModule();
        //hibernate5JakartaModule.configure(Hibernate5JakartaModule.Feature.FORCE_LAZY_LOADING, true);
        return hibernate5JakartaModule;
    }
}

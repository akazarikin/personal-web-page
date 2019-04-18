package com.snear.personalpage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PersonalpageApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalpageApplication.class, args);
    }

}

package com.snear.personalpage;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PersonalpageApplication {

    public static void main(String[] args) {
        delay();
        SpringApplication.run(PersonalpageApplication.class, args);
    }


    @SneakyThrows
    private static void delay() {
        String envDelayInSeconds = System.getenv("START_DELAY");
        if (envDelayInSeconds == null) {
            return;
        }
        Integer delay = Integer.parseInt(envDelayInSeconds);

        System.out.println(String.format("Pausing system for %s seconds", delay));
        for (int i = 0; i < delay; i++) {
            System.out.println(String.format("%s..", (delay - i)));
            Thread.sleep(1000);
        }
    }
}

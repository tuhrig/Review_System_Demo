package de.tuhrig.rsd.checking.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class CheckingSystem {

    public static void main(String[] args) {
        SpringApplication.run(CheckingSystem.class, args);
    }
}

package de.tuhrig.rsd.review.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class ReviewSystem {

    public static void main(String[] args) {
        SpringApplication.run(ReviewSystem.class, args);
    }
}

package de.tuhrig.rsd.statistic.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class StatisticsSystem {

    public static void main(String[] args) {
        SpringApplication.run(StatisticsSystem.class, args);
    }
}

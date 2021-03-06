package com.times.lonefitbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class LonefitBatchApplication {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.default", "dev");
        SpringApplication.run(LonefitBatchApplication.class, args);
    }

}

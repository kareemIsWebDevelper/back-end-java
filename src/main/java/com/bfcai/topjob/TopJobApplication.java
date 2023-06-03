package com.bfcai.topjob;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TopJobApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TopJobApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Backend started successfully");
    }
}

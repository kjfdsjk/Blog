package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo39Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo39Application.class, args);
        System.out.println("http://localhost:8082/home");
    }

}

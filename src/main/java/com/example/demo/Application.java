package com.example.demo;

import com.example.demo.transform.Contact2Json;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        Contact2Json.Contact2Json("com.example.demo.bean.Message");
    }
}

package com.example.libraryfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.libraryfinder.controller")
public class LibraryFinderApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibraryFinderApplication.class, args);
    }
}

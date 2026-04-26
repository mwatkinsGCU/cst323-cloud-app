package com.gcu.cst323;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * CST-323 Cloud Test Application - Entry Point
 * Extends SpringBootServletInitializer to support WAR deployment
 * on cloud platforms like Azure, AWS, and Heroku.
 */
@SpringBootApplication
public class Cst323Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Cst323Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Cst323Application.class, args);
    }
}

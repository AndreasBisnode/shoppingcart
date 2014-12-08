package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"repository.*", "web.*","*","mongo.*"})
@EnableAutoConfiguration

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
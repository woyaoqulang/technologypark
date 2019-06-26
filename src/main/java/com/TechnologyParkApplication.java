package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author DELL
 */
@SpringBootApplication
@ComponentScan({"com.clare"})
public class TechnologyParkApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechnologyParkApplication.class, args);
    }

}

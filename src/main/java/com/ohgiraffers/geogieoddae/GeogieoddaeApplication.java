package com.ohgiraffers.geogieoddae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GeogieoddaeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeogieoddaeApplication.class, args);
    }

}

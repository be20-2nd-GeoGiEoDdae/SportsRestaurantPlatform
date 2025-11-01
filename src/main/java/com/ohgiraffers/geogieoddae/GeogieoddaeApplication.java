package com.ohgiraffers.geogieoddae;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@MapperScan("com.ohgiraffers.geogieoddae.restaurant.query.mapping")
public class GeogieoddaeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeogieoddaeApplication.class, args);
    }

}

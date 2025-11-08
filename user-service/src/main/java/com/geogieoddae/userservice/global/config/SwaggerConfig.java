package com.geogieoddae.userservice.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Geogieoddae REST API 명세서",
                version = "v1.0",
                description = "식당, 키워드, 관람, 리뷰 등 모든 도메인의 API 명세를 제공합니다."
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Local Server")
        }
)
@Configuration
public class SwaggerConfig {
}

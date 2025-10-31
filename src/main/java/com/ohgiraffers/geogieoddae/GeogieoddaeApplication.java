package com.ohgiraffers.geogieoddae;

import com.ohgiraffers.geogieoddae.admin.command.entity.AdminEntity;
import com.ohgiraffers.geogieoddae.admin.command.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableJpaAuditing
public class GeogieoddaeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeogieoddaeApplication.class, args);
    }

    // 관리자용 계정 추가 코드
    @Bean
    public CommandLineRunner initAdminData(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            // 1. "admin" ID를 가진 관리자가 있는지 확인
            if (!adminRepository.findByAdminId("admin").isPresent()) {
                    // 2. "admin" 비밀번호를 BCrypt 로 암호화
                    String encodedPassword = passwordEncoder.encode("admin");

                    // 3. 암호화된 비밀번호로 AdminEntity 생성
                    AdminEntity admin = AdminEntity.builder()
                            .adminId("admin")
                            .adminPassword(encodedPassword)
                            .adminRefreshToken("initial_token")
                            .adminRefreshTokenExpiresAt(LocalDateTime.now())
                            .build();

                    // 4. 데이터베이스에 저장
                    adminRepository.save(admin);
                    System.out.println("======== 테스트용 관리자 계정 (admin/admin) 생성 완료 =======");
            }
        };
    }
}

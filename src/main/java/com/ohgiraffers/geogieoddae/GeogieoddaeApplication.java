package com.ohgiraffers.geogieoddae;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
@EnableJpaAuditing
@MapperScan(basePackages = "com.ohgiraffers.geogieoddae.**.query.mapper")
public class GeogieoddaeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeogieoddaeApplication.class, args);
    }
    /*
    // 더미 회원 데이터 생성 로직 추가
    @Bean
    public CommandLineRunner initData(AdminRepository adminRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            // 관리자 생성 로직
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

            // 더미 회원 데이터 생성 로직
            if (userRepository.count() == 0) {
                System.out.println("======= 더미 회원 데이터 20개 생성 시작 =======");
                IntStream.rangeClosed(1, 20).forEach(i -> {
                    UserEntity user = UserEntity.builder()
                            .userName("회원" + i)
                            .userEmail("user" + i + "@geogi.com")
                            .userPhoneNumber("010-1234-" + String.format("%04d", i))
                            .userRole(i % 5 == 0 ? UserRole.ENTREPRENEUR : UserRole.USER)
                            .userRefreshToken("initial_token")
                            .userRefreshTokenExpiresAt(LocalDateTime.now())
                            .build();
                    userRepository.save(user);
                });
                System.out.println("====== 더미 회원 데이터 생성완료 =======");
            }
        };

    }
    */
}

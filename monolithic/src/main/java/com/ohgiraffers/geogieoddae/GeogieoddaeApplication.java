package com.ohgiraffers.geogieoddae;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.IntStream;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ohgiraffers.geogieoddae.admin.command.entity.AdminEntity;
import com.ohgiraffers.geogieoddae.admin.command.repository.AdminRepository;
import com.ohgiraffers.geogieoddae.auth.command.entity.entrepreneur.EntrepreneurEntity;
import com.ohgiraffers.geogieoddae.auth.command.entity.entrepreneur.EntrepreneurStatus;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserRole;
import com.ohgiraffers.geogieoddae.auth.command.repository.EntrepreneurRepository;
import com.ohgiraffers.geogieoddae.auth.command.repository.UserRepository;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@MapperScan(basePackages = "com.ohgiraffers.geogieoddae.**.query.mapper")
public class GeogieoddaeApplication {

  public static void main(String[] args) {
    SpringApplication.run(GeogieoddaeApplication.class, args);
  }
  
  // 더미 회원 데이터 생성 로직 추가 (주석처리 - 필요시 주석 해제)
  @Bean
  public CommandLineRunner initData(AdminRepository adminRepository, UserRepository userRepository,
      EntrepreneurRepository entrepreneurRepository, PasswordEncoder passwordEncoder) {
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
          String email = "user" + i + "@geogi.com";
          System.out.println("Generated email: " + email);
          UserEntity user = UserEntity.builder()
              .userName("회원" + i)
              .userEmail(email)
              .userPhoneNumber("010-1234-" + String.format("%04d", i))
              .userRole(i % 5 == 0 ? UserRole.ENTREPRENEUR : UserRole.USER)
              .userRefreshToken("initial_token")
              .userRefreshTokenExpiresAt(LocalDateTime.now())
              .customerKey(UUID.randomUUID().toString())
              .build();

          // 저장 후 반환값을 사용하여 userCode 등 영속화된 값을 확보합니다.
          UserEntity savedUser = userRepository.save(user);
          System.out.println("Saved user with email: " + email + " (userCode=" + savedUser.getUserCode() + ")");

          // ENTREPRENEUR 역할인 경우 EntrepreneurEntity도 생성하여 연관관계를 설정합니다.
          if (savedUser.getUserRole() == UserRole.ENTREPRENEUR) {
            EntrepreneurEntity entrepreneur = EntrepreneurEntity.builder()
                .entrepreneurId(savedUser.getUserCode() != null ? savedUser.getUserCode().intValue() : null)
                .entrepreneurCertificateUrl("https://example.com/cert/" + savedUser.getUserCode())
                .entrepreneurBankAccount(100000000 + i) // 더미 계좌번호
                .entrepreneurStatus(EntrepreneurStatus.WAITING)
                .member(savedUser)
                .build();
            entrepreneurRepository.save(entrepreneur);
            System.out.println("Created entrepreneur for userCode=" + savedUser.getUserCode());
          }
        });
        System.out.println("====== 더미 회원 데이터 생성완료 =======");
      }
     };
   
   }

}

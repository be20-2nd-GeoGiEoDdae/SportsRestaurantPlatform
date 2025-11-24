package com.ohgiraffers.geogieoddae.auth.command.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  // JpaRepository 상속받으면 기본적인 CRUD 기능을 모두 사용할 수 있음.
  // ✅ 이메일로 사용자 조회
  Optional<UserEntity> findByUserEmail(String userEmail);
  // ✅  조회
  Optional<UserEntity> findByUserRole(String userRole);

  Optional<UserEntity> findByCustomerKey(String customerKey);
  // 활성 회원만 조회 (탈퇴하지 않은 회원)
  List<UserEntity> findByIsWithdrawnFalse();
  // 탈퇴한 회원 조회
  List<UserEntity> findByIsWithdrawnTrue();
}

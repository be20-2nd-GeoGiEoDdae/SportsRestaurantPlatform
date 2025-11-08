package com.geogieoddae.userservice.auth.command.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geogieoddae.userservice.auth.command.entity.user.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // JpaRepository 상속받으면 기본적인 CRUD 기능을 모두 사용할 수 있음.
    // ✅ 이메일로 사용자 조회
    Optional<UserEntity> findByUserEmail(String userEmail);
    // ✅  조회
    Optional<UserEntity> findByUserRole(String userRole);
}

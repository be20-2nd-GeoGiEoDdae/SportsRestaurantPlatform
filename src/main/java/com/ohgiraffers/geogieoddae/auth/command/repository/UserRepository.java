package com.ohgiraffers.geogieoddae.auth.command.repository;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // JpaRepository 상속받으면 기본적인 CRUD 기능을 모두 사용할 수 있음.
}

package com.ohgiraffers.geogieoddae.auth.command.repository;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}

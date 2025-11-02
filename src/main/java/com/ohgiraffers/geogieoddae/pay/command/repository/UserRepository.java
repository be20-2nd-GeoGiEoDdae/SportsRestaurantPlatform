package com.ohgiraffers.geogieoddae.pay.command.repository;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}

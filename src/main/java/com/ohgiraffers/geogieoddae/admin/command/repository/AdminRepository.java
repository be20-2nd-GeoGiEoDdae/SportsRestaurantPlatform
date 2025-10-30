package com.ohgiraffers.geogieoddae.admin.command.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ohgiraffers.geogieoddae.admin.command.entity.AdminEntity;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    Optional<AdminEntity> findByAdminId(String adminId);
    Optional<AdminEntity> findByAdminRefreshToken(String adminRefreshToken);
}

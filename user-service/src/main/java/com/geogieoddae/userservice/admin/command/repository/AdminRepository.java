package com.geogieoddae.userservice.admin.command.repository;

import com.geogieoddae.userservice.admin.command.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    Optional<AdminEntity> findByAdminId(String adminId);
    Optional<AdminEntity> findByAdminRefreshToken(String adminRefreshToken);
}

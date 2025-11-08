package com.ohgiraffers.geogieoddae.report.command.repository;

import com.ohgiraffers.geogieoddae.report.command.entity.ReportTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportTypeRepository extends JpaRepository<ReportTypeEntity, Long> {
}

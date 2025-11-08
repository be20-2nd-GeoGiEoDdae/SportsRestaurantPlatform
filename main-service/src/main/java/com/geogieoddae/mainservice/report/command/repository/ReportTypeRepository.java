package com.geogieoddae.mainservice.report.command.repository;

import com.geogieoddae.mainservice.report.command.entity.ReportTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportTypeRepository extends JpaRepository<ReportTypeEntity, Long> {
}

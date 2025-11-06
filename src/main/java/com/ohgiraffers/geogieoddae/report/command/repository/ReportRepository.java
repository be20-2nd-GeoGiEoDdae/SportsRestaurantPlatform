package com.ohgiraffers.geogieoddae.report.command.repository;

import com.ohgiraffers.geogieoddae.report.command.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {




}
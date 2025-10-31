//package com.ohgiraffers.geogieoddae.announcement.query.service;
//
//import com.ohgiraffers.geogieoddae.announcement.query.dto.AnnouncementQueryDto;
//import com.ohgiraffers.geogieoddae.announcement.command.entity.AnnouncementEntity;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Transactional(readOnly = true)
//public class AnnouncementService {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    // 목록 조회 (간단 버전)
//    public List<AnnouncementQueryDto> list(int page, int size) {
//        List<AnnouncementEntity> rows = em.createQuery("""
//                select a
//                from AnnouncementEntity a
//                order by a.createdAt desc, a.announcementCode desc
//            """, AnnouncementEntity.class)
//                .setFirstResult(page * size)
//                .setMaxResults(size)
//                .getResultList();
//
//        return rows.stream().map(AnnouncementMapper::toDto).toList();
//    }
//
//    // 상세 조회
//    public AnnouncementQueryDto detail(Long code) {
//        AnnouncementEntity a = em.createQuery("""
//                select a
//                from AnnouncementEntity a
//                join fetch a.admin
//                where a.announcementCode = :code
//            """, AnnouncementEntity.class)
//                .setParameter("code", code)
//                .getSingleResult();
//
//        return AnnouncementMapper.toDto(a);
//    }
//}
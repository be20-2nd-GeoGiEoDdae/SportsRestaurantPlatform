//package com.ohgiraffers.geogieoddae.announcement.query.controller;
//
//import com.ohgiraffers.geogieoddae.announcement.query.dto.AnnouncementQueryDto;
//import com.ohgiraffers.geogieoddae.announcement.query.service.AnnouncementService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/announcements")
//@RequiredArgsConstructor
//public class AnnouncementQueryController {
//
//    private final AnnouncementService service;
//
//    @GetMapping
//    public ResponseEntity<List<AnnouncementQueryDto>> list(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "20") int size
//    ) {
//        return ResponseEntity.ok(service.list(page, size));
//    }
//
//    @GetMapping("/{code}")
//    public ResponseEntity<AnnouncementQueryDto> detail(@PathVariable Long code) {
//        return ResponseEntity.ok(service.detail(code));
//    }
//}

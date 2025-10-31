package com.ohgiraffers.geogieoddae.announcement.command.controller;


import com.ohgiraffers.geogieoddae.announcement.command.DTO.AnnouncementCreate;
import com.ohgiraffers.geogieoddae.announcement.command.DTO.AnnouncementUpdateDto;
import com.ohgiraffers.geogieoddae.announcement.command.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/announcements")
@RequiredArgsConstructor
public class AnnouncementCommandController {
    private final AnnouncementService service;

    @PostMapping("/code")
    public ResponseEntity<Long> create(@RequestBody AnnouncementCreate req) {
        return ResponseEntity.ok(service.create(req));
    }

//    @PatchMapping("/code")
//    public ResponseEntity<Void> update(@PathVariable Long code, @RequestBody AnnouncementUpdateDto req) {
//        service.update(code, req);
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping("/code")
//    public ResponseEntity<Void> delete(@PathVariable Long code) {
//        service.delete(code);
//        return ResponseEntity.noContent().build();
//    }

}

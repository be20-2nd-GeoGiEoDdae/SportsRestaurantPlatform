package com.geogieoddae.mainservice.announcement.command.entity;

import com.geogieoddae.mainservice.global.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "announcement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncementEntity extends BaseTimeEntity {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcement_code")
    private Long announcementCode;

//    @Column(name = "announcement_author", nullable = false)
//    private String announcementAuthor;

    @Column(name = "announcement_title", nullable = false)
    private String announcementTitle;

    @Column(name = "announcement_content", nullable = false)
    private String announcementContent;
//
//    @ManyToOne
//    @JoinColumn(name = "admin_code", nullable = false)
//    private AdminEntity admin;
}

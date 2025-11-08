package com.geogieoddae.mainservice.restaurant.command.entity.review;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "review_picture")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewPictureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_picture_id")
    private Long reviewPictureId;

    @Column(name = "review_picture_url", nullable = false, length = 512)
    private String reviewPictureUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_code", nullable = false)
    private ReviewEntity review;
}

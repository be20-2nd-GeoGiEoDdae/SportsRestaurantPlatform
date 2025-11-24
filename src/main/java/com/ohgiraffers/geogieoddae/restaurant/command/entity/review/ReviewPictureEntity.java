package com.ohgiraffers.geogieoddae.restaurant.command.entity.review;

import com.ohgiraffers.geogieoddae.restaurant.command.entity.review.ReviewEntity;
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
    @Column(name = "review_code")
    private Long reviewCode;

    @Column(name = "review_picture_url", nullable = false, length = 512)
    private String reviewPictureUrl;

    @ManyToOne
    @JoinColumn(name = "review_code", insertable = false, updatable = false)
    private ReviewEntity review;
}

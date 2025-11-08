package com.ohgiraffers.geogieoddae.restaurant.command.entity.review;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.global.common.entity.BaseTimeEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.dto.RestaurantDto;
import com.ohgiraffers.geogieoddae.restaurant.command.dto.ReviewDto;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewEntity extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_code")
    private Long reviewCode;

    @Column(name = "review_title", nullable = false)
    private String reviewTitle;

    @Column(name = "review_body", nullable = false)
    private String reviewBody;

    @Column(name = "review_score", nullable = false)
    private Integer reviewScore;

    @ManyToOne
    @JoinColumn(name = "restaurant_code", nullable = false)
    private RestaurantEntity restaurant;

    @ManyToOne
    @JoinColumn(name = "user_code", nullable = false)
    private UserEntity member;

    @OneToMany(mappedBy = "review",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewPictureEntity> pictures;

//    public static void updatedReview(ReviewDto dto, ReviewEntity review) {
//        review.setReviewTitle(dto.getReviewTitle());
//        review.setReviewBody(dto.getReviewBody());
//        review.setReviewScore(dto.getReviewScore());
//        review.setMember();
//        review.setRestaurant();
//    }
}

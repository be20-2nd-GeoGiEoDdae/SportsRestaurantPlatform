package com.geogieoddae.mainservice.restaurant.command.entity.review;

import com.geogieoddae.mainservice.global.common.entity.BaseTimeEntity;
import com.geogieoddae.mainservice.restaurant.command.entity.restaurant.RestaurantEntity;
import jakarta.persistence.*;
import lombok.*;

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

    private Long memberCode;

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

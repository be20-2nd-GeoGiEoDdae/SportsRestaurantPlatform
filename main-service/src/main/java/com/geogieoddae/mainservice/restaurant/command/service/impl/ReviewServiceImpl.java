package com.geogieoddae.mainservice.restaurant.command.service.impl;

import com.geogieoddae.mainservice.client.UserFeignClient;
import com.geogieoddae.mainservice.client.UserInfoResponse;
import com.geogieoddae.mainservice.restaurant.command.dto.ReviewDto;
import com.geogieoddae.mainservice.restaurant.command.entity.restaurant.RestaurantEntity;
import com.geogieoddae.mainservice.restaurant.command.entity.review.ReviewEntity;
import com.geogieoddae.mainservice.restaurant.command.entity.review.ReviewPictureEntity;
import com.geogieoddae.mainservice.restaurant.command.repository.restaurant.RestaurantRepository;
import com.geogieoddae.mainservice.restaurant.command.repository.review.ReviewPictureRepository;
import com.geogieoddae.mainservice.restaurant.command.repository.review.ReviewRepository;
import com.geogieoddae.mainservice.restaurant.command.service.ReviewService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final ReviewPictureRepository reviewPictureRepository;
    private final ApplicationEventPublisher publisher;
    private final UserFeignClient userFeignClient;

    @Transactional
    @Override
    public void registerReview(ReviewDto reviewDto, Long restaurantId, Long userId, List<MultipartFile> pictures) throws IOException {
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 식당입니다."));
        UserInfoResponse user = userFeignClient.getUserById(userId);

        ReviewEntity review = ReviewEntity.builder()
                .reviewTitle(reviewDto.getReviewTitle())
                .reviewBody(reviewDto.getReviewBody())
                .reviewScore(reviewDto.getReviewScore())
                .restaurant(restaurant)
                .memberCode(user.getUserCode())
                .build();

/*      Long notificationReviewType=(long)5;
      publisher.publishEvent(new AlarmCreatedEvent(restaurant
          .getEntrepreneur()
          .getMember()
          .getUserCode(),notificationReviewType));*/
        reviewRepository.save(review);

        List<String> pictureUrls = saveReviewPictures(review, pictures);
        reviewDto.setPictureUrls(pictureUrls);
        reviewDto.setRestaurantCode(restaurant.getRestaurantCode());
        reviewDto.setUserCode(user.getUserCode());
    }

    @Transactional
    @Override
    public void updateReview(Long reviewId, ReviewDto reviewDto, List<MultipartFile> pictures) throws IOException {
        ReviewEntity review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다."));

        review.setReviewTitle(reviewDto.getReviewTitle());
        review.setReviewBody(reviewDto.getReviewBody());
        review.setReviewScore(reviewDto.getReviewScore());

        if (reviewDto.getRestaurantCode() != null) {
            RestaurantEntity restaurant = restaurantRepository.findById(reviewDto.getRestaurantCode())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 식당입니다."));
            review.setRestaurant(restaurant);
        }

        if (reviewDto.getUserCode() != null) {
            UserInfoResponse user = userFeignClient.getUserById(reviewDto.getUserCode());
        }

        List<ReviewPictureEntity> existingPictures = reviewPictureRepository.findByReview(review);
        if (existingPictures != null && !existingPictures.isEmpty()) {
            for (ReviewPictureEntity pic : existingPictures) {
                String filePath = System.getProperty("user.dir") + "/uploads/"
                        + pic.getReviewPictureUrl().replace("/images/", "");
                File file = new File(filePath);
                if (file.exists()) file.delete();
            }
            reviewPictureRepository.deleteAll(existingPictures);
        }
        List<String> pictureUrls = saveReviewPictures(review, pictures);
        reviewDto.setPictureUrls(pictureUrls);

        reviewRepository.save(review);
    }

    private List<String> saveReviewPictures(ReviewEntity review, List<MultipartFile> pictures) throws IOException {
        List<String> pictureUrls = new ArrayList<>();

        if (pictures != null && !pictures.isEmpty()) {
            List<ReviewPictureEntity> picEntities = new ArrayList<>();

            for (MultipartFile file : pictures) {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                String uploadDir = System.getProperty("user.dir") + "/uploads/";
                File dest = new File(uploadDir + fileName);
                dest.getParentFile().mkdirs();
                file.transferTo(dest);

                String fileUrl = "/images/" + fileName;
                pictureUrls.add(fileUrl);

                picEntities.add(ReviewPictureEntity.builder()
                        .review(review)
                        .reviewPictureUrl(fileUrl)
                        .build());
            }

            reviewPictureRepository.saveAll(picEntities);
        }
        return pictureUrls;
    }

    @Override
    public void deleteReviewAdmin(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Transactional
    @Override
    public void deleteReviewUser(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}

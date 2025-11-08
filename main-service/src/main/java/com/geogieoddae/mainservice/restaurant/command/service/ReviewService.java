package com.geogieoddae.mainservice.restaurant.command.service;

import com.geogieoddae.mainservice.restaurant.command.dto.ReviewDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ReviewService {
    //리뷰 등록
    void registerReview(ReviewDto reviewDto, Long userId, Long restaurantId, List<MultipartFile> pictures) throws IOException;
    //리뷰 삭제
    void deleteReviewAdmin(Long reviewId);
//    //작성한 리뷰 삭제
    void deleteReviewUser(Long reviewId);
    //라뷰 수정
    void updateReview(Long reviewId, ReviewDto reviewDto,List<MultipartFile> pictures) throws IOException;
}

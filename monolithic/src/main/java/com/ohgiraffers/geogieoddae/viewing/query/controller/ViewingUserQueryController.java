package com.ohgiraffers.geogieoddae.viewing.query.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.pay.query.dto.PaySearchResponse;
import com.ohgiraffers.geogieoddae.viewing.query.dto.ViewingUserResponseDto;
import com.ohgiraffers.geogieoddae.viewing.query.service.ViewingUserQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/viewings/user")
@RequiredArgsConstructor
public class ViewingUserQueryController {
  private final ViewingUserQueryService viewingUserQueryService;
  @GetMapping("/{userCode}")
  public ResponseEntity<ApiResponse<Page<ViewingUserResponseDto>>> viewingByUserCode(@PathVariable Long userCode,@PageableDefault Pageable pageable){
    Page<ViewingUserResponseDto>page=viewingUserQueryService.viewingUserList(userCode,pageable);
    return ResponseEntity.ok(ApiResponse.success(page));
  }

}

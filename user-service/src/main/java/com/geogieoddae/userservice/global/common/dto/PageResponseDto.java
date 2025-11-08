/* 페이지 정보 : 페이징된 실제 데이터 + 페이지 정보
 * @Author : 김성현
 * @Date : 2025-11-01
 * @Version : 1.0
 */

package com.geogieoddae.userservice.global.common.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class PageResponseDto<T> {
    private List<T> users;
    // private List<T> data;
    private PageInfoDto pageInfo;
}
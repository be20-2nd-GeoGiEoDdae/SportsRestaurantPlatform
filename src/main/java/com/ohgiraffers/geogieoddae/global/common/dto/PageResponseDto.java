/* 페이지 정보 : 페이징된 실제 데이터 + 페이지 정보
 * @Author : 김성현
 * @Date : 2025-11-01
 * @Version : 1.0
 */

package com.ohgiraffers.geogieoddae.global.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class PageResponseDto<T> {
    private List<T> data;
    private PageInfoDto pageInfo;
}
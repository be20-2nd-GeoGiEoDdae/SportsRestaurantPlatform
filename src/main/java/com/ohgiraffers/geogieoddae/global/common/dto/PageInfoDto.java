/* 페이지 정보 : 현재 페이지, 총 페이지 수
 * @Author : 김성현
 * @Date : 2025-11-01
 * @Version : 1.0
 */

package com.ohgiraffers.geogieoddae.global.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageInfoDto {
    private int page;
    private int size;
    private int totalElements;
    private int totalPages;

    public PageInfoDto(int page, int size, int totalElements) {
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = (int) Math.ceil((double) totalElements / size);
        if (this.totalPages == 0) this.totalPages = 1;    // 데이터가 없을 때도 1페이지로 표시
    }
}

package com.ohgiraffers.geogieoddae.viewing.query.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewingDto {
    private Long viewingCode;
    private String viewingTitle;
    private String viewingBody;
    private LocalDateTime viewingAt;
    private Integer viewingTotalDeposit;
    private String viewingStatus;
    private Integer viewingMinNum;
    private Integer viewingMaxNum;

    private String restaurantName;
    private String sportName;
    private String teamName;

    private Double distance;
    private String pictureUrl;

}

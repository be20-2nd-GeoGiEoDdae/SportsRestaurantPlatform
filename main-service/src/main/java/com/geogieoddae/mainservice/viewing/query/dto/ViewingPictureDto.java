package com.geogieoddae.mainservice.viewing.query.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewingPictureDto {
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
    private List<String> pictureUrls;
}

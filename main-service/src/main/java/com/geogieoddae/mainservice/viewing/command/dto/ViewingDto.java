package com.geogieoddae.mainservice.viewing.command.dto;

import com.geogieoddae.mainservice.viewing.command.entity.ViewingStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ViewingDto {
    @NotNull
    private Long viewingCode;
    @NotNull
    private String viewingTitle;
    @NotNull
    private String viewingBody;
    @NotNull
    private LocalDateTime viewingAt;
    @NotNull
    private Integer viewingTotalDeposit;
    @NotNull
    private ViewingStatus viewingStatus;
    @NotNull
    private Integer viewingMinNum;
    @NotNull
    private Integer viewingMaxNum;
    @NotNull
    private Long restaurantId;
    @NotNull
    private Long sportsId;
    @NotNull
    private List<Long> teamId;

}

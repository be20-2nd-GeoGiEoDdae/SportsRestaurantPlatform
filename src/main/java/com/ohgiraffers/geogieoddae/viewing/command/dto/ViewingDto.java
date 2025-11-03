package com.ohgiraffers.geogieoddae.viewing.command.dto;

import com.ohgiraffers.geogieoddae.pay.command.entity.ViewingPayEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantEntity;
import com.ohgiraffers.geogieoddae.sports.command.entity.SportEntity;
import com.ohgiraffers.geogieoddae.sports.command.entity.TeamEntity;
import com.ohgiraffers.geogieoddae.viewing.command.entity.ViewingStatus;
import com.ohgiraffers.geogieoddae.viewing.command.entity.ViewingUserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ViewingDto {
    private Long viewingCode;
    private String viewingTitle;
    private String viewingBody;
    private LocalDateTime viewingAt;
    private Integer viewingTotalDeposit;
    private ViewingStatus viewingStatus;
    private Integer viewingMinNum;
    private Integer viewingMaxNum;
    private Long restaurantId;
    private Long sportsId;
    private List<Long> teamId;

}

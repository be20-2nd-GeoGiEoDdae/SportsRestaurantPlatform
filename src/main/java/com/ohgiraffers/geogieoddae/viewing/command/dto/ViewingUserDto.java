package com.ohgiraffers.geogieoddae.viewing.command.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewingUserDto {
    private Long viewingUserCode;
    private Integer viewingUserDeposit;
    private Boolean viewingUserIsAttend;
    private Long viewingCode;
    private Long userCode;
}

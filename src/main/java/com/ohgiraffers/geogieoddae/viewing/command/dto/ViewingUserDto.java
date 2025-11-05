package com.ohgiraffers.geogieoddae.viewing.command.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewingUserDto {
    @NotNull
    private Long viewingUserCode;
    @NotNull
    private Integer viewingUserDeposit;
    @NotNull
    private Boolean viewingUserIsAttend;
    @NotNull
    private Long viewingCode;
    @NotNull
    private Long userCode;
}

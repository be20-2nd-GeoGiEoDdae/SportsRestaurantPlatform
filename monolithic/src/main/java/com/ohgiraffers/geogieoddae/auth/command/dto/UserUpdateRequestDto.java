package com.ohgiraffers.geogieoddae.auth.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserUpdateRequestDto {

    private String userName;
    private String userPhoneNumber;
    private String userAddress;

}

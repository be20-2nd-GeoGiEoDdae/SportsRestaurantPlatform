package com.geogieoddae.userservice.auth.command.dto;


import com.geogieoddae.userservice.auth.command.entity.user.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserUpdateResponseDto {

    private String userName;
    private String userEmail;
    private String userPhoneNumber;
    private String userAddress;
    private UserRole userRole;

}

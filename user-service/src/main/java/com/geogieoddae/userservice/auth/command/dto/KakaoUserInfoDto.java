package com.geogieoddae.userservice.auth.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoUserInfoDto {
    private String email;
    private String nickname;
    private String profileImage;
}

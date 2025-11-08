package com.geogieoddae.mainservice.client;

import lombok.Data;

@Data
public class UserInfoResponse {
    private Long userCode;
    private String userName;
    private String userEmail;
}

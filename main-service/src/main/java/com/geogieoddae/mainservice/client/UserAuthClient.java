package com.geogieoddae.mainservice.client;

import com.nimbusds.openid.connect.sdk.UserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserAuthClient {

    @GetMapping("/auth/userinfo")
    UserInfoResponse getUserInfo(@RequestParam("accessToken") String accessToken);
}

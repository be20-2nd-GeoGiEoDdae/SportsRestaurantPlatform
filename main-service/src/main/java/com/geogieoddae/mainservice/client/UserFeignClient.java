package com.geogieoddae.mainservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service-user")
public interface UserFeignClient {

    @GetMapping("/users/{userCode}")
    UserInfoResponse getUserById(@PathVariable("userCode") Long userCode);
}

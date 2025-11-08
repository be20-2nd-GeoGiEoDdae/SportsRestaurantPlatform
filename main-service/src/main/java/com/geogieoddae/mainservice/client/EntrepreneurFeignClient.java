package com.geogieoddae.mainservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service-entrepreneur", path = "/entrepreneurs")
public interface EntrepreneurFeignClient {

    @GetMapping("/{entrepreneurId}")
    Long getEntrepreneurById(@PathVariable("entrepreneurId") Long entrepreneurId);
}
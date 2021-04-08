package com.guli.sta.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="service-ucenter")
@Component
public interface UcenterFeign {
    @PostMapping("/ucenter/member/getRegByDay")
    int getRegByDay(@RequestParam("day") String day);
}

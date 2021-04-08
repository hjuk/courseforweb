package com.guli.order.feign;

import com.guli.order.entity.Member;
import com.guli.order.feign.impl.UcenterFeignImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="service-ucenter")
@Component
public interface UcenterFeign {

    @PostMapping("/ucenter/member/getinfobyid")
    public Member getinfobyid(@RequestParam("id") String id);
}

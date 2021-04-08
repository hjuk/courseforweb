package com.guli.edu.feign;

import com.guli.edu.feign.impl.UcenterFeignImpl;
import com.guli.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@FeignClient(name="service-order")
@Component
public interface OrderFeign {

    @GetMapping("/order/getorderinfobyuser")
    public boolean getOrderInfoByUser(@RequestParam("courseId") String courseId,@RequestParam("userId") String userId);
}

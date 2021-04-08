package com.guli.edu.feign;

import com.guli.edu.feign.impl.UcenterFeignImpl;
import com.guli.edu.feign.impl.VodFeignImpl;
import com.guli.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@FeignClient(name="service-ucenter",fallback = UcenterFeignImpl.class)
@Component
public interface UcenterFeign {

    @PostMapping("/ucenter/member/getinfo")
    public R relogin(@RequestParam("httpReq")HttpServletRequest httpReq);
}

package com.guli.edu.feign;

import com.guli.edu.feign.impl.VodFeignImpl;
import com.guli.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name="service-vod",fallback = VodFeignImpl.class)
@Component
public interface VodFeign {

    @PostMapping("/eduvod/video/delectbyid")
    public R delectbyId(@RequestParam("id") String id);

    @PostMapping("/eduvod/video/delectable")
    public R delectable(@RequestBody List<String> id);
}

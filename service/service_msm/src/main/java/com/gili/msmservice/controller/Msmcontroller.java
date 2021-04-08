package com.gili.msmservice.controller;


import com.gili.msmservice.service.MsmService;
import com.gili.msmservice.untils.RandomUntils;
import com.guli.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/edumsm/msm")
public class Msmcontroller {
    @Autowired
    private MsmService msmService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/send")
    public R sendMsm(String phone){
        String code=redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)){
            return R.success();
        }
        code= RandomUntils.getFourFormat();
        Map<String, Object> param=new HashMap<>();
        param.put("code",code);
        boolean isSend=msmService.sendMsm(param,phone);
        if (!isSend) {
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.failed();
        }
        return R.success();
    }
}

package com.guli.order.feign.impl;


import com.guli.order.entity.Member;
import com.guli.order.feign.UcenterFeign;
import org.springframework.stereotype.Component;

@Component
public class UcenterFeignImpl implements UcenterFeign {



    @Override
    public Member getinfobyid(String id) {
        return  null;
    }
}

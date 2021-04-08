package com.guli.edu.feign.impl;

import com.guli.edu.feign.VodFeign;
import com.guli.utils.R;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodFeignImpl implements VodFeign {
    @Override
    public R delectbyId(String id) {
        return  R.failed().massage("timeout");
    }

    @Override
    public R delectable(List<String> id) {
        return  R.failed().massage("timeout");
    }
}

package com.guli.edu.feign.impl;

import com.guli.edu.feign.UcenterFeign;
import com.guli.edu.feign.VodFeign;
import com.guli.utils.R;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class UcenterFeignImpl implements UcenterFeign {

    @Override
    public R relogin(HttpServletRequest httpReq) {
        return  R.failed().massage("timeout");
    }
}

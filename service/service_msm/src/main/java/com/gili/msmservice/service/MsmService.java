package com.gili.msmservice.service;

import java.util.Map;

public interface MsmService {
    boolean sendMsm(Map<String, Object> param,String phone);
}

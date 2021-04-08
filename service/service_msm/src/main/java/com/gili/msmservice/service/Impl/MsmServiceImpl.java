package com.gili.msmservice.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.gili.msmservice.service.MsmService;
import org.springframework.util.StringUtils;

import java.util.Map;

public class MsmServiceImpl implements MsmService {

    @Override
    public boolean sendMsm(Map<String, Object> param, String phone) {
        if (StringUtils.isEmpty(phone)) {
            return false;
        }

        DefaultProfile profile=
                DefaultProfile.getProfile("default","","");
        IAcsClient client=new DefaultAcsClient(profile);

        CommonRequest request=new CommonRequest();
        //固定设置
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");//服务地址
        request.setSysVersion("2017-05-25");//版本号
        request.setSysAction("SendSms");//服务设置

        request.putQueryParameter("PhoneNumbers",phone);
        request.putQueryParameter("SignName","");//签名名称
        request.putQueryParameter("TemplateCode","");//模板code
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));//验证码
        try {
            CommonResponse commonResponse = client.getCommonResponse(request);
             return  commonResponse.getHttpResponse().isSuccess();
        } catch (ClientException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }




    }
}

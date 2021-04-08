package com.guli.ucenter.controller;

import com.google.gson.Gson;
import com.guli.ucenter.entity.Member;
import com.guli.ucenter.service.MemberService;
import com.guli.ucenter.untils.HttpClientUtils;
import com.guli.ucenter.untils.WxUntils;
import com.guli.utils.GuliException;
import com.guli.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;

@Controller
@RequestMapping("/ucenter/wx")
public class WxApiController {

    @Autowired
    private MemberService memberService;


    @GetMapping("callback")
    public String callback(String code,String state){

        try {
            String baseurl="https://api.weixin.qq.com/sns/oauth2/access_token"+
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";

            String url = String.format(
                    baseurl,
                    WxUntils.APP_ID,
                    WxUntils.APP_SECRET,
                    code);

            System.out.println(code+state);

            String s = HttpClientUtils.get(url);

            Gson gson=new Gson();
            HashMap hashMap = gson.fromJson(s, HashMap.class);
            String access_token = (String) hashMap.get("access_token");
            String openid = (String) hashMap.get("openid");


            Member member = memberService.getByOpenId(openid);
            String token="";
            if (member==null){
                String info="https://api.weixin.qq.com/sns/userinfo"+
                        "?access_token=%s" +
                        "&openid=%s";
                String urlinfo = String.format(
                        info,
                        access_token,
                        openid);
                String s1 = HttpClientUtils.get(urlinfo);

                HashMap hashMap1 = gson.fromJson(s1, HashMap.class);
                String nickname = (String) hashMap1.get("nickname");
                String headimgurl = (String) hashMap1.get("headimgurl");
                Member members=new Member();
                members.setOpenid(openid);
                members.setNickname(nickname);
                members.setAvatar(headimgurl);
                members.setGmtCreate(new Date());
                members.setGmtModified(new Date());
                memberService.save(member);
                 token= JwtUtils.getJwtToken(members.getId(),members.getNickname());

            }
            return "redirect:http://localhost:3000?token="+token;

        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(20001,"登陆失败");
        }

    }


    @GetMapping("wchatlogin")
    public String WChatLogin() throws UnsupportedEncodingException {
        String baseurl="https://open.weixin.qq.com/connect/qrconnect"+
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";
        String redirect=WxUntils.REDIRECT_URL;
        redirect=URLEncoder.encode(redirect, StandardCharsets.UTF_8);

        String url = String.format(
                baseurl,
                WxUntils.APP_ID,
                redirect,
                "guli");

        return "redirect:"+url;
    }
}

package com.guli.ucenter.controller;


import com.guli.ucenter.entity.Member;
import com.guli.ucenter.entity.vo.RegisterVo;
import com.guli.ucenter.service.MemberService;
import com.guli.utils.JwtUtils;
import com.guli.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author dtt
 * @since 2021-03-31
 */
@RestController
@RequestMapping("/ucenter/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("register")
    public R register(@RequestBody RegisterVo register){
        memberService.register(register);
        return R.success();
    }

    @PostMapping("login")
    public R login(@RequestBody Member member){
       String token=memberService.login(member);
        return R.success().data("token",token);
    }
    @GetMapping("getinfo")
    public R relogin(HttpServletRequest httpReq){
        String id=JwtUtils.getIdByToken(httpReq);
        Member byId = memberService.getById(id);
        byId.setPassword("");
        return R.success().data("userinfo",byId);
    }
    @PostMapping("getinfobyid")
    public Member getinfobyid(String id){
        Member byId = memberService.getById(id);
        byId.setPassword("");
        return byId;
    }
    @PostMapping("getRegByDay")
    public int getRegByDay(String day){

        return memberService.countRegByDay(day);
    }


}


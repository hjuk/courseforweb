package com.guli.ucenter.service;

import com.guli.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.ucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author dtt
 * @since 2021-03-31
 */
public interface MemberService extends IService<Member> {

    String login(Member member);

    void register(RegisterVo register);

    Member getByOpenId(String openid);

    int countRegByDay(String day );
}

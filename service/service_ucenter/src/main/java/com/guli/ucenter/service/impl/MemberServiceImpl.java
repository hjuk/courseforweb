package com.guli.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.ucenter.entity.Member;
import com.guli.ucenter.entity.vo.RegisterVo;
import com.guli.ucenter.mapper.MemberMapper;
import com.guli.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.utils.GuliException;
import com.guli.utils.JwtUtils;
import com.guli.utils.MD5;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author dtt
 * @since 2021-03-31
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String login(Member member) {
        String phone = member.getMobile();
        String password=member.getPassword();

        if(StringUtils.isEmpty(phone)|| StringUtils.isEmpty(phone)){
           throw new GuliException(20001,"账号或密码为空");
        }
        QueryWrapper<Member> query=new QueryWrapper<>();
        query.eq("mobile",phone);
        Member member1 = baseMapper.selectOne(query);
        if (member1 == null) {
            throw new GuliException(20001,"手机号不存在");
        }

        if (!member1.getPassword().equals(MD5.encode(password))) {
            throw new GuliException(20001,"密码错误");
        }
        if (member1.getIsDisabled()) {
            throw new GuliException(20001,"账户异常");
        }

        return JwtUtils.getJwtToken(member1.getId(),member1.getNickname());
    }

    @Override
    public void register(RegisterVo register) {
        if(StringUtils.isEmpty(register.getCode())||StringUtils.isEmpty(register.getNickname())||
                StringUtils.isEmpty(register.getPassword())||StringUtils.isEmpty(register.getMobile())){
            throw new GuliException(20001,"注册失败");
        }
        if (!register.getCode().equals(redisTemplate.opsForValue().get(register.getMobile())) ) {
            throw new GuliException(20001,"注册失败，验证码错误");
        }
        QueryWrapper<Member> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("mobile",register.getMobile());
        Integer integer = baseMapper.selectCount(queryWrapper);
        if(integer>0){
            throw new GuliException(200001,"手机号已存在");
        }
        Member member=new Member();
        member.setMobile(register.getMobile());
        member.setNickname(register.getNickname());
        member.setPassword(MD5.encode(register.getPassword()));
        member.setIsDisabled(false);
        member.setAvatar("1111111111");
        member.setGmtCreate(new Date());
        member.setGmtModified(new Date());
        baseMapper.insert(member);



    }

    @Override
    public Member getByOpenId(String openid) {
        QueryWrapper<Member> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("openid",openid);

        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public int countRegByDay(String day) {
        return baseMapper.countRegByDay(day);
    }
}

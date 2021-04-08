package com.guli.ucenter.mapper;

import com.guli.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author dtt
 * @since 2021-03-31
 */
@Mapper
public interface MemberMapper extends BaseMapper<Member> {

    int countRegByDay(String day);
}

package com.guli.acl.service.impl;

import com.guli.acl.entity.User;
import com.guli.acl.mapper.UserMapper;
import com.guli.acl.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author dtt
 * @since 2021-04-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

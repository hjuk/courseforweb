package com.guli.acl.service;

import com.guli.acl.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author dtt
 * @since 2021-04-06
 */
public interface PermissionService extends IService<Permission> {

    Map<String, Object> getAllPermission();

    void deleteById(String id);

    void addById(String id);
}

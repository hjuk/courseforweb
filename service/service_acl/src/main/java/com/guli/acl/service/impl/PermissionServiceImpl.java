package com.guli.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.acl.entity.Permission;
import com.guli.acl.entity.RolePermission;
import com.guli.acl.mapper.PermissionMapper;
import com.guli.acl.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.acl.service.RolePermissionService;
import com.guli.acl.untils.BuildUntils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author dtt
 * @since 2021-04-06
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {


    @Resource
    private RolePermissionService rolePermissionService;
    @Override
    public Map<String, Object> getAllPermission() {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("type");
        List<Permission> permissions = baseMapper.selectList(queryWrapper);

        return BuildUntils.buildPermission(permissions);
    }

    @Override
    public void deleteById(String id) {
        List<String> list=new ArrayList<>();
        this.idlist(id,list);
        list.add(id);
        baseMapper.deleteBatchIds(list);


    }
    //数据库压力略大
    public  void idlist(String id,List<String> list){
        QueryWrapper<Permission> query=new QueryWrapper<>();
        query.eq("pid",id);
        query.select("id");
        List<Permission> permissions = baseMapper.selectList(query);
        permissions.forEach(it->{
            list.add(it.getId());
            this.idlist(it.getId(),list);
        });
    }


    @Override
    public void addById(String id, String[] permissionsid) {
        List<RolePermission> rolePermissions=new ArrayList<>();

        for (String s : permissionsid) {
            RolePermission role=new RolePermission();
            role.setRoleId(id);
            role.setPermissionId(s);
            rolePermissions.add(role);
        }
        rolePermissionService.saveBatch(rolePermissions);
    }
}

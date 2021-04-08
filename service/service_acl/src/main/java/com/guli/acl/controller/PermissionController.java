package com.guli.acl.controller;


import com.guli.acl.service.PermissionService;
import com.guli.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author dtt
 * @since 2021-04-06
 */
@RestController
@RequestMapping("/acl/permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @GetMapping("getAll")
    public R getAllPermission(){
        Map<String, Object> permissions = permissionService.getAllPermission();
        return R.success().data("permissions",permissions);
    }

    @GetMapping("delete")
    public R deleteById(String id){
        permissionService.deleteById(id);
        return R.success();
    }
    //给用户添加权限
    @GetMapping("addrole")
    public R addById(String id,String[] permissionsid){
        permissionService.addById(id,permissionsid);
        return R.success();
    }


}


package com.guli.acl.untils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.acl.entity.Permission;
import org.springframework.stereotype.Component;

import java.security.Permissions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BuildUntils {
    public static Map<String,Object> buildPermission(List<Permission> permissions){
        List<Permission> firstNode=new ArrayList<>();
Map<String,Object> map = new HashMap<>();
        for (Permission permission : permissions) {
            if ("0".equals(permission.getPid())) {
                permission.setLevel(1);

                map.put(permission.getName(),getChildren(permission,permissions));
            }

        }


        return map;
    }

    public static Permission getChildren(Permission firstNode, List<Permission> permissions){
        firstNode.setChildren(new ArrayList<>());

        permissions.forEach(p -> {
            if (firstNode.getId().equals(p.getPid())) {
                int level=firstNode.getLevel()+1;
                p.setLevel(level);
                firstNode.getChildren().add(getChildren(p,permissions));
            }
        });

        return firstNode;
    }


}

package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.entity.Course;
import com.guli.edu.entity.Teacher;
import com.guli.edu.mapper.TeacherMapper;
import com.guli.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-23
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {



    @Override
    public List<Teacher> seleteTeacherHot() {
        QueryWrapper<Teacher> query = new QueryWrapper<>();
        query.orderByDesc("sort");
        //8
        query.last("limit 4");
        return baseMapper.selectList(query);
    }

    @Override
    public Map<String, Object> frontpage(Page<Teacher> page, Object o) {
        QueryWrapper<Teacher> query = new QueryWrapper<>();
        query.orderByDesc("sort");
        baseMapper.selectPage(page, query);

        Map<String, Object> map=new HashMap<>();
        map.put("records",page.getRecords());
        map.put("current",page.getCurrent());
        map.put("pages",page.getPages());
        map.put("size",page.getSize());
        map.put("total",page.getTotal());
        map.put("hasNext",page.hasNext());//下一页
        map.put("hasPrevious",page.hasPrevious());//上一页
        return map;
    }
}

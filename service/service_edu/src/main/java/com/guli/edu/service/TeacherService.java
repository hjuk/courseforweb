package com.guli.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-23
 */
public interface TeacherService extends IService<Teacher> {

    List<Teacher> seleteTeacherHot();

    Map<String, Object> frontpage(Page<Teacher> page, Object o);
}

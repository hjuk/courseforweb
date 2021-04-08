package com.guli.edu.mapper;

import com.guli.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guli.edu.entity.vo.CourseFVo;
import com.guli.edu.entity.vo.CourseInFoVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-03-25
 */
public interface CourseMapper extends BaseMapper<Course> {

    CourseFVo getInfoById(String courseId);
    CourseInFoVo selectByIdCourseFrontInfo(String courseId);

}

package com.guli.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.entity.vo.CourseFVo;
import com.guli.edu.entity.vo.CourseFrontVo;
import com.guli.edu.entity.vo.CourseInFoVo;
import com.guli.edu.entity.vo.CourseVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-25
 */
public interface CourseService extends IService<Course> {

    void saveCourse(CourseVo courseVo);

    void updateCourse(CourseVo courseVo);

    CourseVo selectByIdCourseInfo(String courseId);

    CourseFVo selectInfo(String courseId);

    void delectCourse(String courseId);

    List<Course> seleteCourseHot();

    Map<String, Object> getCourseFrontList(Page<Course> course, CourseFrontVo courseFrontVo);


    CourseInFoVo selectByIdCourseFrontInfo(String courseId);
}

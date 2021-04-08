package com.guli.edu.controller;


import com.guli.edu.entity.Course;
import com.guli.edu.entity.vo.CourseVo;
import com.guli.edu.service.CourseService;
import com.guli.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-25
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("add")
    public R addCourseInfo(@RequestBody CourseVo courseVo){

        courseService.saveCourse(courseVo);

        return R.success();
    }
    @PostMapping("update")
    public R updateCourseInfo(@RequestBody CourseVo courseVo){

        courseService.updateCourse(courseVo);

        return R.success();
    }

    @PostMapping("selectById")
    public R selectByIdCourseInfo(String courseId){



        return R.success().data("courseVo",courseService.selectByIdCourseInfo(courseId));
    }
    @PostMapping("selectinfo")
    public R selectInfo(String courseId){



        return R.success().data("courseFVo",courseService.selectInfo(courseId));
    }
    @PostMapping("pubpost")
    public R pubPost(String courseId){
        Course course=new Course();
        course.setId(courseId);
        course.setStatus("Normal");
        course.setGmtModified(new Date());
        courseService.updateById(course);


        return R.success();
    }
    @PostMapping("delect")
    public R delectCourse(String courseId){

        courseService.delectCourse(courseId);

        return R.success();
    }
    @PostMapping("getByIdfororder")
    public Course getByIdCourseInfo(String courseId){
        return courseService.getById(courseId);
    }
}


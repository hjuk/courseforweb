package com.guli.edu.controller.Front;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.entity.Course;
import com.guli.edu.entity.chapter.ChapterVo;
import com.guli.edu.entity.vo.CourseFrontVo;
import com.guli.edu.entity.vo.CourseInFoVo;
import com.guli.edu.entity.vo.CourseVo;
import com.guli.edu.feign.OrderFeign;
import com.guli.edu.service.ChapterService;
import com.guli.edu.service.CourseService;
import com.guli.utils.JwtUtils;
import com.guli.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-25
 */
@RestController
@RequestMapping("/eduservice/course/front")
@CrossOrigin
public class CourseFrontController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private OrderFeign     orderFeign;

    @PostMapping("getfrontcoureslist")
    public R getFrontCouresList(@RequestBody(required = false) CourseFrontVo courseFrontVo,int index,int limit){

        Page<Course> course=new Page<>(index,limit);
       Map<String,Object> map=courseService.getCourseFrontList(course,courseFrontVo);




//        .data("courseVo",);
        return R.success().data("courseVo",map);
    }



    @PostMapping("selectById")
    public R selectByIdCourseInfo(String courseId, HttpServletRequest httpRequest){
        List<ChapterVo> chapter = chapterService.getChapter(courseId);
        CourseInFoVo courseInFoVo=courseService.selectByIdCourseFrontInfo(courseId);

        boolean orderInfoByUser = orderFeign.getOrderInfoByUser(courseId, JwtUtils.getIdByToken(httpRequest));

        return R.success().data("chapter",chapter).data("courseInFoVo",courseInFoVo).data("orderInfoByUser",orderInfoByUser);
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

}


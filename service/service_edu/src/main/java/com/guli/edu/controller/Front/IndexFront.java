package com.guli.edu.controller.Front;


import com.guli.edu.service.CourseService;
import com.guli.edu.service.TeacherService;
import com.guli.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eduservice/index")
public class IndexFront {
    //热门课程
    @Autowired
    private CourseService courseService;

    @GetMapping("/cours")
   public R courshot(){

        return R.success().data("course",courseService.seleteCourseHot());
    }
    //名师
    @Autowired
    private TeacherService teacherService;
    @GetMapping("/teacher")
    public R teacherhot(){

        return R.success().data("teacher",teacherService.seleteTeacherHot());
    }
}

package com.guli.edu.controller.Front;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.entity.Course;
import com.guli.edu.entity.Teacher;
import com.guli.edu.entity.vo.TeacherQuery;
import com.guli.edu.service.CourseService;
import com.guli.edu.service.TeacherService;
import com.guli.utils.R;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-23
 */
@RestController
@RequestMapping("/eduservice/teacher/front")
@CrossOrigin
public class TeacherFrontController {

    //1
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;


    @GetMapping("pageTeacher")
    public R PageListTeacher(int index ,int limit){
        Page<Teacher> page=new Page<>(index,limit);

       Map<String,Object> map= teacherService.frontpage(page, null);

        return R.success().data("map",map);

    }

    @RequestMapping("pageTeacherCondtion")
    public R pageTeacherCondition( Integer index , Integer limit,@RequestBody TeacherQuery teacherQuery){
        Page<Teacher> page=new Page<>(index,limit);
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        String name=teacherQuery.getName();
        Integer level=teacherQuery.getLevel();
        String begin=teacherQuery.getBeginTime();
        String end = teacherQuery.getEndTime();
        if (!StringUtil.isNullOrEmpty(name) ){
            teacherQueryWrapper.like("name",name);
        }
        if (level!=null){
            teacherQueryWrapper.eq("level",level);
        }
        if (!StringUtil.isNullOrEmpty(begin)){
            teacherQueryWrapper.ge("gmt_create",begin);
        }
        if (!StringUtil.isNullOrEmpty(end)){
            teacherQueryWrapper.le("gmt_create",end);
        }


        teacherService.page(page,teacherQueryWrapper);
        return R.success().data("total",page.getTotal()).data("rows",page.getRecords());


    }



    @GetMapping("getTeacherByid")
    public R getTeacherById(String id){
        Teacher byId = teacherService.getById(id);
        QueryWrapper<Course> course=new QueryWrapper<>();
        course.eq("teacher_id",id);
        List<Course> courses = courseService.list(course);


        return R.success().data("teacher",byId).data("courses",courses);
    }


}


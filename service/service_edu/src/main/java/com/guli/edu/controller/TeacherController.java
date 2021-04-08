package com.guli.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.entity.Teacher;
import com.guli.edu.entity.vo.TeacherQuery;
import com.guli.edu.service.TeacherService;
import com.guli.utils.GuliException;
import com.guli.utils.R;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-23
 */
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class TeacherController {

    //1
    @Autowired
    private TeacherService teacherService;


    @GetMapping("findAll")
    public R findAll(){
        return R.success().data("item",teacherService.list(null));

    }

    @DeleteMapping("delect")
    public R isDeleted(String id){
        if(teacherService.removeById(id)){
            return R.success();
        }else{
            return R.failed();
        }
    }


    @GetMapping("pageTeacher")
    public R PageListTeacher(int index ,int limit){
        Page<Teacher> page=new Page<>(index,limit);

        teacherService.page(page,null);

        return R.success().data("total",page.getTotal()).data("rows",page.getRecords());

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

    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody Teacher teacher){
        teacher.setGmtCreate(new Date());
        teacher.setGmtModified(new Date());
       boolean s= teacherService.save(teacher);
        if(s){
            return R.success();
        }else{
            return R.failed();
        }
    }

    @GetMapping("getTeacherByid")
    public R getTeacherById(String id){

         return R.success().data("item",teacherService.getById(id));
    }

    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody Teacher teacher){
        teacher.setGmtModified(new Date());
//        try {
//            System.out.println(10 / 0);
//        }catch(Exception e){
//            throw new GuliException(2000,"自定义");
//        }
        boolean r=  teacherService.updateById(teacher);
        if(r){
            return R.success();
        }else{
            return R.failed();
        }
    }




}


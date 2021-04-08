package com.guli.edu.controller;


import com.guli.edu.service.SubjectService;
import com.guli.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-25
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;


    @PostMapping("add")
    public R addSubject(MultipartFile file){
        subjectService.saveSubject(file,subjectService);

        return R.success();
    }

    @GetMapping("getAllSubject")
    public R getSubject(){



        return R.success().data("item",subjectService.getAllSubject());
    }


}


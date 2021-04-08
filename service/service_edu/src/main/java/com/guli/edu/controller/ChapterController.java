package com.guli.edu.controller;


import com.guli.edu.entity.Chapter;
import com.guli.edu.service.ChapterService;
import com.guli.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-25
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class ChapterController {

    @Autowired
    private ChapterService chapterService;


    @PostMapping("getcapter")
    public R getcapter(String courseId){


        return R.success().data("chaptervideo", chapterService.getChapter(courseId));
    }
    @PostMapping("insert")
    public R insertcapter(@RequestBody Chapter chapter){

        chapterService.insertChapter(chapter);

        return R.success();
    }
    @PostMapping("update")
    public R updatecapter(@RequestBody Chapter chapter){

        chapterService.updateChapter(chapter);

        return R.success();
    }
    @PostMapping("delectById")
    public R delectByIdcapter(String id){

        int s= chapterService.delectByIdChapter(id);
        if (s==0){
            return R.success();
        }
        return R.failed().data("尚未删除",s);
    }

}


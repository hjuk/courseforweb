package com.guli.edu.controller;


import com.guli.edu.entity.Chapter;
import com.guli.edu.entity.Video;
import com.guli.edu.feign.VodFeign;
import com.guli.edu.service.VideoService;
import com.guli.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-25
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class VideoController {



    @Autowired
    private VideoService videoService;

    @PostMapping("insert")
    public R insertVideo(@RequestBody Video video){

        videoService.insertVideo(video);

        return R.success();
    }
    @PostMapping("update")
    public R updateVideo(@RequestBody Video video){

        videoService.updateVideo(video);

        return R.success();
    }
    @PostMapping("delectById")
    public R deletByIdVideo(String id){
        videoService.delectByIdVideo(id);

        return R.success();

    }


}


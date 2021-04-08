package com.guli.video.controller;

import com.guli.utils.R;
import com.guli.video.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/video")
public class Vodcontroller {
    @Autowired
    private VodService vodService;

    @PostMapping("uploadAliyun")
    public R uploadVideo(MultipartFile file){

        return R.success().data("videoId",vodService.uploadVideo(file));
    }
    @PostMapping("delectbyid")
    public R delectbyId(String id){

        return R.success().data("videoId",vodService.delectbyId(id));
    }
    @PostMapping("delectable")
    public R delectable(List<String> id){
        vodService.delectAble(id);
        return R.success();
    }
    @GetMapping("getplayAuth")
    public R getPlayAuth(String id){
        String audio=vodService.getPlayAuth(id);
       return R.success().data("playAuth",audio);
    }
}

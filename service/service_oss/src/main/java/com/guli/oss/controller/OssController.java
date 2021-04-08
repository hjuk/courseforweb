package com.guli.oss.controller;


import com.guli.oss.service.OssService;
import com.guli.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fileupload")
@CrossOrigin
public class OssController {


    @Autowired
    private OssService ossservice;


    @RequestMapping("picture")
    public R uploadOssFiles(MultipartFile file){

       String url= ossservice.uploadFileAvatar(file);
        return R.success().data("url",url);
    }
}

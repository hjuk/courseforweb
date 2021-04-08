package com.guli.video.service;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
@Component
public interface VodService {
    String uploadVideo(MultipartFile file);

    StringBuffer delectbyId(String id);

    void delectAble(List<String> id);

    String getPlayAuth(String id);
}

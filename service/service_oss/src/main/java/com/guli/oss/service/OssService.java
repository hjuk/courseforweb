package com.guli.oss.service;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Mapper
@Component
public interface OssService {
     String uploadFileAvatar(MultipartFile file);

}

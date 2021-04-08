package com.guli.oss.service.Impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.guli.oss.service.OssService;
import com.guli.oss.until.OssUntil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        try {
            String uuid= UUID.randomUUID().toString().replaceAll("-","");

            String dataPath= new DateTime().toString("yyyy/MM/dd");

            OSS ossclient=new OSSClient(OssUntil.END_PONT,OssUntil.KEY_ID,OssUntil.KEY_SECRET);
            InputStream inputStream=file.getInputStream();
            ossclient.putObject(OssUntil.BUCKET_NAME,dataPath+"/"+uuid+file.getOriginalFilename(), inputStream);


            ossclient.shutdown();
           // https://guli-1101.oss-cn-beijing.aliyuncs.com/java%E9%9D%A2%E8%AF%95%E9%A2%98.docx

            return "https://"+OssUntil.BUCKET_NAME+"."+OssUntil.END_PONT+"/"+dataPath+"/"+uuid+file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }
}

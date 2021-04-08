package com.guli.video.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.guli.utils.GuliException;
import com.guli.video.service.VodService;
import com.guli.video.until.VodUntil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideo(MultipartFile file) {
        String videoId ="";
        try {
            InputStream in = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(VodUntil.KEY_ID,VodUntil.KEY_SECRET,file.getOriginalFilename().substring(0,file.getOriginalFilename().indexOf(".")), file.getOriginalFilename(),in);
            UploadVideoImpl upload=new UploadVideoImpl();
            UploadStreamResponse response=upload.uploadStream(request);

            videoId=response.getVideoId();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return videoId;
    }

    @Override
    public StringBuffer delectbyId(String id) {
        if (id.equals("")){
            throw new GuliException(20001,"id为空");
        }
        StringBuffer videoId= new StringBuffer();
        try {
            //加载配置
            DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", VodUntil.KEY_ID, VodUntil.KEY_SECRET);
            //创建reques
            DeleteVideoRequest request = new DeleteVideoRequest();
            //置入id
            request.setVideoIds(id);
            //加载配置，创建链接
            DefaultAcsClient client = new DefaultAcsClient(profile);
            //提交请求，返回respond
            DeleteVideoResponse response = client.getAcsResponse(request);
            videoId.append(response.getRequestId());


        } catch (ClientException e) {
            e.printStackTrace();
            throw new GuliException(20001,e.getMessage());

        }
        return videoId;
    }

    @Override
    public void delectAble(List<String> id) {
        String r="";

        try {
            //加载配置
            DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", VodUntil.KEY_ID, VodUntil.KEY_SECRET);
            //创建reques
            DeleteVideoRequest request = new DeleteVideoRequest();
            //置入id
            String videoId=String.join(",", id);

            request.setVideoIds(videoId);
            //加载配置，创建链接
            DefaultAcsClient client = new DefaultAcsClient(profile);
            //提交请求，返回respond
            DeleteVideoResponse response = client.getAcsResponse(request);
            r=r+""+response.getRequestId();
        } catch (ClientException e) {
            e.printStackTrace();
            throw new GuliException(20001,e.getMessage()+r);

        }
    }

    @Override
    public String getPlayAuth(String id) {
        String r="";
        try {
            //加载配置
            DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", VodUntil.KEY_ID, VodUntil.KEY_SECRET);
            //创建reques
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            //置入id
            request.setVideoId(id);
            //加载配置，创建链接
            DefaultAcsClient client = new DefaultAcsClient(profile);
            //提交请求，返回respond
            GetVideoPlayAuthResponse acsResponse = client.getAcsResponse(request);
            r=acsResponse.getPlayAuth();
        } catch (ClientException e) {
            e.printStackTrace();
            throw new GuliException(20001,e.getMessage()+r);

        }
        return r;
    }
}

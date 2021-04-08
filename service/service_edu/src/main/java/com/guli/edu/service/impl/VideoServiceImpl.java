package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu.entity.Video;
import com.guli.edu.feign.VodFeign;
import com.guli.edu.mapper.VideoMapper;
import com.guli.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.utils.GuliException;
import com.guli.utils.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-25
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VodFeign vodFeign;

    @Override
    public void insertVideo(Video video) {
        video.setGmtCreate(new Date());
        video.setGmtModified(new Date());
        video.setVersion(1L);
       int i=baseMapper.insert(video);
       if (i ==0){
           throw new GuliException(20001,"小节插入失败");
       }
    }

    @Override
    public void updateVideo(Video video) {
        int i=baseMapper.updateById(video);
        if (i ==0){
            throw new GuliException(20001,"小节修改失败");
        }
    }

    @Override
    public void delectByIdVideo(String id) {

        R result=null;
        Video video = baseMapper.selectById(id);
        if (video ==null){
            throw new GuliException(20001,"该小节不存在");
        }else{
            if (!StringUtils.isEmpty(video.getVideoSourceId()))
              result= vodFeign.delectbyId(video.getVideoSourceId());
            if (result.getCode()==20001){
                throw new GuliException(20001,result.getMassage()+"default");
            }
            baseMapper.deleteById(id);

        }

    }



    @Override
    public void removevideoBycourseId(String courseId) {


        QueryWrapper<Video> query = new QueryWrapper<>();
        query.eq("course_id",courseId);
        query.select("video_source_id");
        List<Video> videos = baseMapper.selectList(query);


        List<String> courseIdList=new ArrayList<>();
        for (Video video : videos) {

            if (!StringUtils.isEmpty(video.getVideoSourceId()))
                courseIdList.add(video.getCourseId());

        }


        if (courseIdList.size()>0)
            vodFeign.delectable(courseIdList);


        QueryWrapper<Video> query1 = new QueryWrapper<>();
        query.eq("course_id",courseId);
        baseMapper.delete(query1);


    }
}

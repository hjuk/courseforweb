package com.guli.edu.service;

import com.guli.edu.entity.Chapter;
import com.guli.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-25
 */
public interface VideoService extends IService<Video> {

    void insertVideo(Video video);

    void updateVideo(Video video);

    void delectByIdVideo(String id);

    void removevideoBycourseId(String courseId);
}

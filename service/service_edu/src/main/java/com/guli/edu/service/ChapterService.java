package com.guli.edu.service;

import com.guli.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-25
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getChapter(String courseId);

    void insertChapter(Chapter chapter);

    void updateChapter(Chapter chapter);

    int delectByIdChapter(String id);

    void removechapterBycourseId(String courseId);
}

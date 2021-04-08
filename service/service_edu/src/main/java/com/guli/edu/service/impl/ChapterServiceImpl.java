package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu.entity.Chapter;
import com.guli.edu.entity.Video;
import com.guli.edu.entity.chapter.ChapterVo;
import com.guli.edu.entity.chapter.VideoVo;
import com.guli.edu.mapper.ChapterMapper;
import com.guli.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.service.VideoService;
import com.guli.utils.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-25
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoService videoService;

    @Override
    public List<ChapterVo> getChapter(String courseId) {
        QueryWrapper<Chapter> query = new QueryWrapper<>();
        query.eq("course_id", courseId);
        List<Chapter> chapterList = baseMapper.selectList(query);

        QueryWrapper<Video> vid = new QueryWrapper<>();
        vid.eq("course_id", courseId);
        List<Video> videoList = videoService.list(vid);

        List<ChapterVo> chapterVoList = new ArrayList<>();

        for (int i = 0; i < chapterList.size(); i++) {
            ChapterVo chapterVoA = new ChapterVo();
            Chapter chapterA = chapterList.get(i);
            BeanUtils.copyProperties(chapterA, chapterVoA);
            chapterVoList.add(chapterVoA);
            List<VideoVo> videoArrayList = new ArrayList<>();
            for (Video video : videoList) {
                if (video.getChapterId().equals(chapterA.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    videoArrayList.add(videoVo);
                }
            }
            chapterVoList.get(i).setCd(videoArrayList);
        }
        return chapterVoList;
    }

    @Override
    public void insertChapter(Chapter chapter) {
        chapter.setGmtCreate(new Date());
        chapter.setGmtModified(new Date());

        int i = baseMapper.insert(chapter);
        if (i == 0) {
            throw new GuliException(20001, "章节新增失败");
        }

    }

    @Override
    public void updateChapter(Chapter chapter) {
        chapter.setGmtModified(new Date());
        int i = baseMapper.updateById(chapter);
        if (i == 0) {
            throw new GuliException(20001, "章节更新失败");
        }
    }

    @Override
    public int delectByIdChapter(String id) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id", id);
        int count = videoService.count(queryWrapper);
        if (count == 0) {
            int i = baseMapper.deleteById(id);


            return count;
        }else {
                return count;
                }

    }

    @Override
    public void removechapterBycourseId(String courseId) {
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        baseMapper.delete(queryWrapper);
    }


}

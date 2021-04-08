package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.entity.Course;
import com.guli.edu.entity.CourseDescription;
import com.guli.edu.entity.vo.CourseFVo;
import com.guli.edu.entity.vo.CourseFrontVo;
import com.guli.edu.entity.vo.CourseInFoVo;
import com.guli.edu.entity.vo.CourseVo;
import com.guli.edu.feign.OrderFeign;
import com.guli.edu.mapper.CourseMapper;
import com.guli.edu.service.ChapterService;
import com.guli.edu.service.CourseDescriptionService;
import com.guli.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.service.VideoService;
import com.guli.utils.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-25
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseDescriptionService courseDescriptionService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private VideoService videoService;


    @Override
    public void saveCourse(CourseVo courseVo) {
        Course course=new Course();
        BeanUtils.copyProperties(courseVo,course);
        course.setGmtCreate(new Date());
        course.setGmtModified(new Date());
        course.setIsDeleted(0);
        int i=baseMapper.insert(course);
        if (i==0){
            throw new GuliException(20001,"false");
        }
        String courseId=course.getId();
        CourseDescription cd=new CourseDescription();
        cd.setId(courseId);
        cd.setGmtCreate(new Date());
        cd.setGmtModified(new Date());
        cd.setDescription(courseVo.getDescription());
        courseDescriptionService.save(cd);




    }

    @Override
    public void updateCourse(CourseVo courseVo) {
        Course course=new Course();
        BeanUtils.copyProperties(courseVo,course);
        course.setGmtModified(new Date());
        int updateCourse= baseMapper.updateById(course);
        if (updateCourse==0){
            throw new GuliException(20001,"修改课程失败");
        }

        CourseDescription cd=new CourseDescription();
        cd.setId(courseVo.getId());
        cd.setDescription(courseVo.getDescription());
        cd.setGmtModified(new Date());
        courseDescriptionService.updateById(cd);



    }

    @Override
    public CourseVo selectByIdCourseInfo(String courseId) {

        Course course = baseMapper.selectById(courseId);

        CourseDescription byId = courseDescriptionService.getById(courseId);
        CourseVo courseVo=new CourseVo();
        BeanUtils.copyProperties(course,courseVo);
        courseVo.setDescription(byId.getDescription());


        return courseVo;
    }

    @Override
    public CourseFVo selectInfo(String courseId) {
     return   baseMapper.getInfoById(courseId);
    }

    @Override
    public void delectCourse(String courseId) {
        //需先删除小节，章节，描述
        videoService.removevideoBycourseId(courseId);
        chapterService.removechapterBycourseId(courseId);
        courseDescriptionService.removeById(courseId);
        int i=baseMapper.deleteById(courseId);
        if(i==0){
            throw new GuliException(20001,"删除失败");
        }
    }

    @Override
    public List<Course> seleteCourseHot() {
        QueryWrapper<Course> query = new QueryWrapper<>();
        query.orderByDesc("view_count");
        //8
        query.last("limit 4");
        return baseMapper.selectList(query);
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<Course> course, CourseFrontVo courseFrontVo) {
        QueryWrapper<Course> query=new QueryWrapper<>();


        if (!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())) {
            query.eq("subject_parent_id",courseFrontVo.getSubjectParentId());
        }

        if (!StringUtils.isEmpty(courseFrontVo.getSubjectId())) {
            query.eq("subject_id",courseFrontVo.getSubjectId());
        }
        if (!StringUtils.isEmpty(courseFrontVo.getPriceSort())) {
            query.orderByDesc("price");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getGmtCreatSort())) {
            query.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getByCountSort())) {
            query.orderByDesc("buy_count");
        }



        baseMapper.selectPage(course,query);

        Map<String, Object> map=new HashMap<>();
        map.put("records",course.getRecords());
        map.put("current",course.getCurrent());
        map.put("pages",course.getPages());
        map.put("size",course.getSize());
        map.put("total",course.getTotal());
        map.put("hasNext",course.hasNext());//下一页
        map.put("hasPrevious",course.hasPrevious());//上一页

        return map;
    }

    @Override
    public CourseInFoVo selectByIdCourseFrontInfo(String courseId) {

        return baseMapper.selectByIdCourseFrontInfo(courseId);
    }


}

package com.guli.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu.Until.listener.SubjectExcelListener;
import com.guli.edu.entity.Subject;
import com.guli.edu.entity.excel.SubjectData;
import com.guli.edu.entity.subjectvo.Subjectone;
import com.guli.edu.entity.subjectvo.Subjecttwo;
import com.guli.edu.mapper.SubjectMapper;
import com.guli.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-25
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void saveSubject(MultipartFile file,SubjectService subjectService) {
        try {
            InputStream in=file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Subjectone> getAllSubject() {

        QueryWrapper<Subject> onew=new QueryWrapper<>();
        onew.eq("parent_id",0);
        List<Subject> subjects = baseMapper.selectList(onew);
        QueryWrapper<Subject> twow=new QueryWrapper<>();
        twow.ne("parent_id",0);
        List<Subject> subjects1 = baseMapper.selectList(twow);

        List<Subjectone> result=new ArrayList<>();

        for (int i = 0; i < subjects.size(); i++) {
            Subjectone one=new Subjectone();
            BeanUtils.copyProperties(subjects.get(i),one);
            result.add(one);
            List<Subjecttwo> subjecttwos = new ArrayList<>();
            for (Subject sub : subjects1) {

                if (sub.getParentId().equals(result.get(i).getId())) {
                    Subjecttwo subjecttwo=new Subjecttwo();
                    BeanUtils.copyProperties(sub, subjecttwo);
                    subjecttwos.add(subjecttwo);

                }
            }
            result.get(i).setSubjecttwoList(subjecttwos);
        }



        return result;
    }
}

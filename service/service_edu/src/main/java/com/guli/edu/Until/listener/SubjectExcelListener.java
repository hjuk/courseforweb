package com.guli.edu.Until.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu.entity.Subject;
import com.guli.edu.entity.excel.SubjectData;
import com.guli.edu.service.SubjectService;
import com.guli.utils.GuliException;

import java.util.Date;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public SubjectService subjectService;




    public SubjectExcelListener() {
    }


    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData==null){
            throw new GuliException(20001,"数据为空");
        }

        Subject oneSubject=this.exisOneSubject(subjectService,subjectData.getOneSubjectName());

        if(oneSubject==null){
            oneSubject = new Subject();
            oneSubject.setParentId("0");
            oneSubject.setTitle(subjectData.getOneSubjectName());
            oneSubject.setGmtCreate(new Date());
            oneSubject.setGmtModified(new Date());
            subjectService.save(oneSubject);
    }

        Subject twoSubject=this.exisTwoSubject(subjectService,subjectData.getTwoSubjectName(),oneSubject.getId());
            if(twoSubject==null){
        twoSubject = new Subject();
        twoSubject.setParentId(oneSubject.getId());
        twoSubject.setTitle(subjectData.getTwoSubjectName());
        twoSubject.setGmtCreate(new Date());
        twoSubject.setGmtModified(new Date());
        subjectService.save(twoSubject);
    }

    }
    private Subject exisOneSubject(SubjectService subjectService,String name){
        QueryWrapper<Subject> wrapper=new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
      return  subjectService.getOne(wrapper);
    }
    private Subject exisTwoSubject(SubjectService subjectService,String name,String pid){
        QueryWrapper<Subject> wrapper=new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        return  subjectService.getOne(wrapper);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

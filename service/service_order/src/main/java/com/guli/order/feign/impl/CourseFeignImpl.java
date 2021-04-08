package com.guli.order.feign.impl;


import com.guli.order.entity.Course;
import com.guli.order.feign.CourseFeign;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseFeignImpl implements CourseFeign {

    @Override
    public Course getByIdCourseInfo(String courseId) {
        return null;
    }
}


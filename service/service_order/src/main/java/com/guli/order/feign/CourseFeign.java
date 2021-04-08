package com.guli.order.feign;


import com.guli.order.entity.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="service-edu")
@Component
public interface CourseFeign {
    @PostMapping("/eduservice/course/getByIdfororder")
    public Course getByIdCourseInfo(@RequestParam("courseId") String courseId);
}

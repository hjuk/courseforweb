package com.guli.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.entity.Comment;
import com.guli.edu.feign.UcenterFeign;
import com.guli.edu.service.CommentService;
import com.guli.utils.JwtUtils;
import com.guli.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-04-03
 */
@RestController
@RequestMapping("/eduservice/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UcenterFeign ucenterFeign;

    @GetMapping("getcomment")
    public R getComment(String courseid,int index,int limit){
        Page<Comment> page = new Page<>(index,limit);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseid);
        commentService.page(page,queryWrapper);
       return R.success().data("comment",page);
    }
    @PostMapping("insert")
    public R insertComment(@RequestBody Comment comment){
        comment.setGmtCreate(new Date());
        comment.setGmtModified(new Date());
        commentService.save(comment);
       return R.success();
    }
    @GetMapping("getinfo")
    public R relogin(HttpServletRequest httpReq){

        return ucenterFeign.relogin(httpReq);
    }

}


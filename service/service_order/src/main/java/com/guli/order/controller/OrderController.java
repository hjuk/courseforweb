package com.guli.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.order.entity.Order;
import com.guli.order.service.OrderService;
import com.guli.utils.JwtUtils;
import com.guli.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-04-04
 */
@RestController
@RequestMapping("/order/")
public class OrderController {


    @Autowired
    private OrderService  orderService;


    @PostMapping("creatorder")
    public R creatOrder(String courseId, HttpServletRequest httpRequest){

       String orderNum=orderService.creatOrder(courseId, JwtUtils.getIdByToken(httpRequest));
       return R.success().data("orderNum",orderNum);
    }
    @GetMapping("getorderinfo")
    public R getOrderInfo(String orderid){
        QueryWrapper<Order> q = new QueryWrapper<>();
        q.eq("order_no",orderid);
        Order one = orderService.getOne(q);
        return R.success().data("oder",one);
    }
    @GetMapping("getorderinfobyuser")
    public boolean getOrderInfoByUser(String courseId,String userId){
        QueryWrapper<Order> q = new QueryWrapper<>();
        q.eq("course_id",courseId);
        q.eq("member_id",userId);
        q.eq("status",1);

        int count = orderService.count(q);
        return count > 1;
    }
}


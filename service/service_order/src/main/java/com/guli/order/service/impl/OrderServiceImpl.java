package com.guli.order.service.impl;

import com.guli.order.entity.Course;
import com.guli.order.entity.Member;
import com.guli.order.entity.Order;
import com.guli.order.feign.CourseFeign;
import com.guli.order.feign.UcenterFeign;
import com.guli.order.mapper.OrderMapper;
import com.guli.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.order.untils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-04-04
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private CourseFeign courseFeign;
    @Autowired
    private UcenterFeign ucenterFeign;

    @Override
    public String creatOrder(String courseId, String idByToken) {
        Course courseInfo = courseFeign.getByIdCourseInfo(courseId);
        Member member = ucenterFeign.getinfobyid(idByToken);
        Order order = new Order();
        order.setCourseId(courseInfo.getId());//课程id
        order.setCourseCover(courseInfo.getCover());//课程封面
        order.setCourseTitle(courseInfo.getTitle());//课程标题
        order.setMemberId(member.getId());//用户id
        order.setMobile(member.getMobile());//手机号
        order.setNickname(member.getNickname());//昵称
        order.setOrderNo(OrderNoUtil.getOrderNo());//订单号
        order.setTeacherName(courseInfo.getTeacherId());//讲师id
        order.setTotalFee(courseInfo.getPrice());//价格
        order.setStatus(0);//支付状态
        order.setPayType(1);//支付方式
        order.setGmtCreate(new Date());
        order.setGmtModified(new Date());
        baseMapper.insert(order);
        return order.getOrderNo();
    }
}

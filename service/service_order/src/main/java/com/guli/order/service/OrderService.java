package com.guli.order.service;

import com.guli.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-04-04
 */
public interface OrderService extends IService<Order> {

    String creatOrder(String courseId, String idByToken);
}

package com.guli.order.service;

import com.guli.order.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-04-04
 */
public interface PayLogService extends IService<PayLog> {

    Map creatNative(String orderId);

    Map<String, String> getPatStatusById(String orderNo);

    void updateOrderStatus(Map<String, String> map);
}

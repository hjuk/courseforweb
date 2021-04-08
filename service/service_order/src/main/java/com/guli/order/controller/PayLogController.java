package com.guli.order.controller;


import com.guli.order.service.PayLogService;
import com.guli.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-04-04
 */
@RestController
@RequestMapping("/order/paylog")
public class PayLogController {
    @Autowired
    private PayLogService payLogServicel;

    @GetMapping("creatNative")
    public R creatNative(String orderId){
       Map map=payLogServicel.creatNative(orderId);
       return R.success().data("info",map);
    }
    @GetMapping("getPatStatusbyId")
    public R getPatStatusById(String orderNo){
        Map<String, String> map=payLogServicel.getPatStatusById(orderNo);
        if(map==null){
            return R.failed().massage("支付失败");
        }
        if (map.get("trade_state").equals("SUCCESS")){
            payLogServicel.updateOrderStatus(map);
            return R.success();
        }
        return R.success().massage("支付中");
    }

}


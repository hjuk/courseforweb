package com.guli.order.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wxpay.sdk.WXPayUtil;
import com.guli.order.entity.Order;
import com.guli.order.entity.PayLog;
import com.guli.order.mapper.PayLogMapper;
import com.guli.order.service.OrderService;
import com.guli.order.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.guli.order.untils.HttpClient;
import com.guli.utils.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-04-04
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

    @Autowired
    private OrderService orderService;

    @Override
    public Map creatNative(String orderId) {
        try {
            //查询订单信息
            QueryWrapper<Order> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("order_no",orderId);;
            Order one = orderService.getOne(queryWrapper);
            //
            Map map=new HashMap();
            map.put("appid","wx74862e0dfcf69954");
            map.put("mch_id","1558950191");
            map.put("nonce_str", WXPayUtil.generateNonceStr());
            map.put("body",one.getCourseTitle());
            map.put("out_trade_on",orderId);
            map.put("total_fee",one.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");
            map.put("spbill_creat_id","127.0.0.1");
            map.put("notify_url","http://guli.shop/api/order/weixinPay/weixinNotify\n");
            map.put("trade_type","NATIVE");

            HttpClient client=new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            client.setXmlParam(WXPayUtil.generateSignedXml(map,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);

            client.post();
            String content = client.getContent();
            Map<String, String> result =WXPayUtil.xmlToMap(content);

            Map infomap=new HashMap();
            infomap.put("out_trade_on",orderId);
            infomap.put("course_id",one.getCourseId());
            infomap.put("total_fee",one.getTotalFee());
            infomap.put("result_code",result.get("result_code"));
            infomap.put("code_url",result.get("code_url"));
            return result;
        }catch(Exception e){
            throw new GuliException(20001,"生成失败");
        }

    }

    @Override
    public Map<String, String> getPatStatusById(String orderNo) {

        try {
            Map map=new HashMap();
            map.put("appid","wx74862e0dfcf69954");
            map.put("mch_id","1558950191");
            map.put("out_trade_on",orderNo);
            map.put("nonce_str", WXPayUtil.generateNonceStr());
            HttpClient client=new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(map,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();


            String content = client.getContent();
            Map<String, String> result =WXPayUtil.xmlToMap(content);

            return result;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public void updateOrderStatus(Map<String, String> map) {
        String s = map.get("out_trade_no");
        QueryWrapper<Order> query=new QueryWrapper<>();
        query.eq("out_trade_no",s);
        Order order= orderService.getOne(query);


        if (order.getStatus() ==1){return;}

        order.setStatus(1);
        order.setGmtModified(new Date());
        orderService.updateById(order);
        PayLog payLog=new PayLog();
        payLog.setOrderNo(order.getOrderNo());
        payLog.setPayTime(new Date());
        payLog.setPayType(1);
        payLog.setTotalFee(order.getTotalFee());
        payLog.setTradeState(map.get("trade_state"));
        payLog.setTransactionId(map.get("transaction_id"));
        payLog.setAttr(JSONObject.toJSONString(map));
        payLog.setGmtCreate(new Date());
        payLog.setGmtModified(new Date());

        baseMapper.insert(payLog);
    }
}

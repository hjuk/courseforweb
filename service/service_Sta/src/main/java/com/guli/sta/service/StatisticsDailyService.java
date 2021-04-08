package com.guli.sta.service;

import com.guli.sta.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-04-05
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    int regCountByDay(String day);

    Map<String, Object> getShowData(String type, String begin, String end);
}

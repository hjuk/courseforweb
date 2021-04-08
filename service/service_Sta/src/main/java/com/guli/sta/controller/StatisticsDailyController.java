package com.guli.sta.controller;


import com.guli.sta.service.StatisticsDailyService;
import com.guli.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/sta/")
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService statisticsDao;

    @PostMapping("regcountbyday")
    public R RegCountByDay(String day){

        return R.success().data("count",statisticsDao.regCountByDay(day));
    }
    @GetMapping("showdata")
    public R showData(String type,String begin,String end){
       Map<String,Object> map=statisticsDao.getShowData(type,begin,end);

        return R.success().data("info",map);
    }



}


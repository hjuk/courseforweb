package com.guli.sta.untils;


import com.guli.sta.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Schedule {


@Autowired
private StatisticsDailyService statisticsDao;

    @Scheduled(cron = "0 0 1 * * ?")
    public void task(){
        statisticsDao.regCountByDay(DateUtil.formatDate(DateUtil.addDays(new Date(),-1)));
    }
}

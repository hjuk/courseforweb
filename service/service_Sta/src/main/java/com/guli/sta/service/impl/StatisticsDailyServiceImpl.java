package com.guli.sta.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.sta.entity.StatisticsDaily;
import com.guli.sta.feign.UcenterFeign;
import com.guli.sta.mapper.StatisticsDailyMapper;
import com.guli.sta.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-04-05
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {
@Autowired
private UcenterFeign ucenterFeign;

    @Override
    public int regCountByDay(String day) {
        int regByDay = ucenterFeign.getRegByDay(day);
        QueryWrapper<StatisticsDaily> query = new QueryWrapper<>();
        query.eq("date_calculated",day);
        Integer integer = baseMapper.selectCount(query);
        StatisticsDaily statistics=baseMapper.selectOne(query);
        statistics.setRegisterNum(regByDay);
        statistics.setCourseNum(100);
        statistics.setLoginNum(100);
        statistics.setVideoViewNum(100);
        statistics.setDateCalculated(day);
        if(integer==0){

            statistics.setGmtCreate(new Date());
            statistics.setGmtModified(new Date());
            baseMapper.insert(statistics);
        }
        statistics.setGmtModified(new Date());
        baseMapper.updateById(statistics);


        return regByDay;
    }

    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {
        QueryWrapper<StatisticsDaily> query = new QueryWrapper<>();
        query.between("date_calculated",begin,end);
        query.select("date_calculated",type);


        List<StatisticsDaily> statisticsDailies = baseMapper.selectList(query);
        Map<String, Object> map = new HashMap<>();
        List<String> date=new ArrayList<>();
        List<Integer> data=new ArrayList<>();
        for (StatisticsDaily statisticsDaily : statisticsDailies) {
            date.add(statisticsDaily.getDateCalculated());
            switch(type){
                case "register_num":data.add(statisticsDaily.getRegisterNum());
                    break;
                case  "login_num":data.add(statisticsDaily.getLoginNum());
                    break;
                case  "video_view_num":data.add(statisticsDaily.getVideoViewNum());
                    break;
                case  "course_num":data.add(statisticsDaily.getCourseNum());
                    break;
                default:  break;

            }

        }
        map.put("date",date);
        map.put("data",data);
        return map;
    }


}

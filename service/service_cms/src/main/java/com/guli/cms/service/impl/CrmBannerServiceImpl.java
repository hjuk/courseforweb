package com.guli.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.cms.entity.CrmBanner;
import com.guli.cms.mapper.CrmBannerMapper;
import com.guli.cms.service.CrmBannerService;
import com.guli.utils.R;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-30
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    public R getBannerPage(int index, int limit) {
        Page<CrmBanner> page=new Page<>(index,limit);
        baseMapper.selectPage(page,null);

        return R.success().data("item",page.getRecords()).data("total",page.getTotal());
    }

    @Cacheable(key="'SelectAll'",value="getBanner")
    @Override
    public List<CrmBanner> SelectAll() {
        QueryWrapper<CrmBanner> query=new QueryWrapper<>();
        query.orderByDesc("sort");
        query.last("limit 1");
        return baseMapper.selectList(query);
    }
}

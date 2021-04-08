package com.guli.cms.service;

import com.guli.cms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.utils.R;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-30
 */
public interface CrmBannerService extends IService<CrmBanner> {

    R getBannerPage(int index, int limit);

    List<CrmBanner> SelectAll();
}

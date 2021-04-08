package com.guli.cms.controller;


import com.guli.cms.entity.CrmBanner;
import com.guli.cms.service.CrmBannerService;
import com.guli.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-30
 */
@RestController
@RequestMapping("/cms/bannerAdmin")
public class CrmBannerControllerAdmin {

    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("getpage")
    public R getBanner(int index,int limit){

        return crmBannerService.getBannerPage(index, limit);
    }
    @GetMapping("getbyid")
    public R getBannerByid(int id){

        return R.success().data("banner",crmBannerService.getById(id));
    }

    @PostMapping("insert")
    public R insertBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.save(crmBanner);
        return R.success();
    }
    @PostMapping("update")
    public R updateBanner(@RequestBody CrmBanner crm){
        crmBannerService.updateById(crm);
        return R.success();
    }
    @PostMapping("delete")
    public R deleteBanner(int id){
        crmBannerService.removeById(id);
        return R.success();
    }
    }




package com.guli.cms.controller;


import com.guli.cms.service.CrmBannerService;
import com.guli.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-30
 */
@RestController
@RequestMapping("/cms/banner")
public class CrmBannerController {

    @Autowired
    private CrmBannerService crmBannerService;


    @GetMapping("getpage")
    public R getBanner(){

        return R.success().data("list",crmBannerService.SelectAll());
    }
    @GetMapping("getbyid")
    public R getBannerByid(int id){

        return R.success().data("banner",crmBannerService.getById(id));
    }

}


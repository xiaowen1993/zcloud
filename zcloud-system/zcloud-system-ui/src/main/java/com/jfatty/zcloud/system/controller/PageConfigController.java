package com.jfatty.zcloud.system.controller;


import com.jfatty.zcloud.base.controller.IBaseController;
import com.jfatty.zcloud.base.utils.RELResultUtils;
import com.jfatty.zcloud.system.entity.PageConfig;
import com.jfatty.zcloud.system.feign.PageConfigFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 界面开发配置 前端控制器
 * </p>
 *
 * @author jfatty
 * @since 2019-12-13
 */
@Slf4j
@RestController
@RequestMapping("/pageConfig")
public class PageConfigController  implements IBaseController<PageConfig> {

    @Autowired
    private PageConfigFeignClient pageConfigFeignClient;

    @Override
    public RELResultUtils<PageConfig> table(Map<String, Object> params) {
        return pageConfigFeignClient.table(params);
    }

    @Override
    public RELResultUtils<PageConfig> table(String v, Integer pageIndex, Integer pageSize) {
        return pageConfigFeignClient.table(v,pageIndex,pageSize);
    }

    @Override
    public Object list() {
        return pageConfigFeignClient.list();
    }

    @Override
    public List<PageConfig> list(Long v) {
        return pageConfigFeignClient.list(v);
    }

    @Override
    public Object save(PageConfig entity) {
        return pageConfigFeignClient.save(entity);
    }

    @Override
    public Object view(String id) {
        return pageConfigFeignClient.view(id);
    }

    @Override
    public Object edit(PageConfig entity) {
        return pageConfigFeignClient.edit(entity);
    }

    @Override
    public Object delete(Map<String, Object> params) {
        return pageConfigFeignClient.delete(params);
    }

}


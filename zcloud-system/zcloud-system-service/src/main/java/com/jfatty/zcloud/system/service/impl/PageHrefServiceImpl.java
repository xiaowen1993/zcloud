package com.jfatty.zcloud.system.service.impl;

import com.jfatty.zcloud.system.entity.PageHref;
import com.jfatty.zcloud.system.mapper.PageHrefMapper;
import com.jfatty.zcloud.system.service.PageHrefService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 界面链接跳转开发配置 服务实现类
 * </p>
 *
 * @author jfatty
 * @since 2020-01-01
 */
@Slf4j
@Service
public class PageHrefServiceImpl extends BaseSystemServiceImpl<PageHref,PageHrefMapper> implements PageHrefService {

    private PageHrefMapper pageHrefMapper ;

    @Autowired
    public void setPageHrefMapper(PageHrefMapper pageHrefMapper) {
        super.setBaseMapper(pageHrefMapper);
        this.pageHrefMapper = pageHrefMapper;
    }

    @Override
    public List<PageHref> getPageHrefsByIds(String appId, String pageId) {
        return pageHrefMapper.getPageHrefsByIds(appId,pageId);
    }

    @Override
    public List<PageHref> getPageHrefsOpts(String appId, String hospitalId, String verifyName, String verifyRule) {
        return pageHrefMapper.getPageHrefsOpts(appId,hospitalId,verifyName,verifyRule);
    }
}

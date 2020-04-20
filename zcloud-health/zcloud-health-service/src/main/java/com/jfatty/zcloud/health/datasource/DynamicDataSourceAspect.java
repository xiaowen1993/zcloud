package com.jfatty.zcloud.health.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 切换数据源Advice
 */
@Slf4j
@Aspect
@Order(-1)// 保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {


    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, TargetDataSource ds) throws Throwable {
        String dsId = ds.name();
        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
            log.error("数据源[{}]不存在，使用默认数据源 > {}", ds.name(), point.getSignature());
        } else {
            log.info("Use DataSource : {} > {}", dsId, point.getSignature());
            DynamicDataSourceContextHolder.setDataSourceType(dsId);
        }
    }

    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, TargetDataSource ds) {
        log.info("Revert DataSource : {} > {}", ds.name(), point.getSignature());
        DynamicDataSourceContextHolder.clearDataSourceType();
    }

}

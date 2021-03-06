package com.jfatty.zcloud.system.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.jfatty.zcloud.base.config.BaseConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 描述 MybatisPlus配置类
 *
 * @author jfatty on 2019/8/26
 * @email jfatty@163.com
 */
//factoryBean = XaDynamicMapperFactoryBean.class,
@Configuration
@MapperScan( basePackages = {"com.jfatty.zcloud.*.mapper"})
public class MybatisPlusConfig extends BaseConfig {

    /**
     * mybatis-plus分页插件<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return super.paginationInterceptor();
    }


    /**
     * SQL执行效率插件
     * 性能分析拦截器，不建议生产使用
     */
    @Bean
    @Profile({"dev","local"})// 设置 dev local 环境开启
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        /*<!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 -->*/
        performanceInterceptor.setMaxTime(180000);
        /*<!--SQL是否格式化 默认false-->*/
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

}

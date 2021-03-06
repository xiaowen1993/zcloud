package com.jfatty.zcloud.auth.mapper;

import com.jfatty.zcloud.auth.entity.AuthSmsConfig;
import com.jfatty.zcloud.base.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 系统短信息配置表 Mapper 接口
 * </p>
 *
 * @author jfatty
 * @since 2019-12-27
 */
public interface AuthSmsConfigMapper extends IBaseMapper<AuthSmsConfig> {

    AuthSmsConfig getByAppId(@Param("appId") String appId);
}

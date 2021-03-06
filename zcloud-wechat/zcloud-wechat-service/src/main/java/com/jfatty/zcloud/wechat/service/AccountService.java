package com.jfatty.zcloud.wechat.service;


import com.jfatty.zcloud.wechat.entity.Account;

/**
 * <p>
 * 微信账号表 服务类
 * </p>
 *
 * @author jfatty
 * @since 2019-04-04
 */
public interface AccountService extends BaseWechatService<Account> {

    /**
     * 根据微信账号信息进行查询
     * @param account 微信账号
     * @return
     */
    Account getByAccount(String account);

    /**
     * 获取系统当前出去激活状态微信账号
     * @return
     */
    Account getActiveAccount();

    /**
     * 根据微信账号信息进行查询
     * @param appId appId
     * @return
     */
    Account getByAppId(String appId);

    void updateUsingState(String appId);

    Account getByUsingState();
}

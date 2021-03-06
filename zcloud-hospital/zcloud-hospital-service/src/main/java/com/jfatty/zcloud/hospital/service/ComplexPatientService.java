package com.jfatty.zcloud.hospital.service;

import com.jfatty.zcloud.hospital.res.NumoPatientDeatilRes;
import com.jfatty.zcloud.hospital.res.WebRegPatientRes;
import com.jfatty.zcloud.hospital.vo.NumoPatientInfo;
import com.jfatty.zcloud.hospital.vo.NumoUserInfo;
import com.jfatty.zcloud.hospital.vo.WebRegPatient;

import java.util.List;

/**
 * 描述
 *
 * @author jfatty on 2019/12/16
 * @email jfatty@163.com
 */
public interface ComplexPatientService {

    /**
     * 查询微信/支付宝用户绑定的就诊人的信息
     * @param openId
     * @param openIdType
     * @return
     */
    List<WebRegPatient> getWebRegList(String openId, Integer openIdType);
    /**
     * 查询微信/支付宝用户绑定的就诊人的信息
     * @param openId
     * @param openIdType
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<WebRegPatient> getWebRegList(String openId, Integer openIdType, Integer pageIndex, Integer pageSize);

    /**
     *
     * @param openId
     * @param openIdType
     * @param name 姓名
     * @param idCard 身份证号码
     * @param tel 电话号码
     * @param address 地址
     * @param nation 民族
     * @param hisCardNo 就诊卡号
     * @param hisCardType
     * @return
     */
    boolean saveComplexPatient(String openId, Integer openIdType, String name, String gender,Integer age,
                               String birthdayStr,String idCard, String tel, String address, String nation,String relationship,Integer hasCard, String hisCardNo, String hisCardType) throws Exception ;


    NumoPatientDeatilRes getNumoPatientInfo(String openId, String brid);
    //查看就诊人详情 身份证加星操作
    /**
     * 查询单个就诊人详情  jfatty 2017-10-24
     * @param brid 病人ID
     * @return
     */
    NumoPatientDeatilRes getNumoPatientInfo(String openId, Integer openIdType, String brid);

    //删除就诊人  抛出异常
    /**
     * 删除就诊人  抛出异常
     * @param idCard 身份证号码
     * @param name 姓名
     * @param openId 微信支付宝openId
     * @param openIdType 微信支付宝openId类型
     * @return
     * @throws Exception
     */
    boolean deleteComplexPatient(Long pid, String idCard, String name, String openId, Integer openIdType) throws Exception ;

    NumoPatientInfo getNumoPatientInfoByBrid(String brid);

    /**
     * 查询用户有无操作就诊人的权限
     * @param openId 微信支付宝openId
     * @param openIdType 微信支付宝openId类型
     * @param brid 病人ID
     * @return
     */
    boolean checkRightByBrid(String openId, Integer openIdType, String brid);

    /**
     * 绑定默认就诊人
     * @param openId
     * @param openIdType
     * @param brid
     * @return
     */
    boolean bindDefaultPat(String openId, Integer openIdType, String brid,Integer bindStatus) throws Exception ;

    /**
     * 查询用户是否存在
     * @param openId 微信支付宝openId
     * @param openIdType 微信支付宝openId类型
     * @param attention 用户关注或者取消关注状态
     * @return
     */
    boolean isExist(String openId, Integer openIdType, int attention);

    /**
     * 关注公众号事件,添加用户信息  jfatty  2017-10-24
     * 存在则修改时间，不存在则新增用户
     * @param openId 微信支付宝openId
     * @param openIdType 微信支付宝openId类型
     * @param attention 用户关注或者取消关注状态
     */
    void subscribeEvent(String openId, Integer openIdType, int attention) throws Exception ;

    /**
     *
     * @param openId 微信支付宝openId
     * @param openIdType 微信支付宝openId类型
     * @return
     */
    NumoUserInfo getNumoUserInfo(String openId, Integer openIdType);
}

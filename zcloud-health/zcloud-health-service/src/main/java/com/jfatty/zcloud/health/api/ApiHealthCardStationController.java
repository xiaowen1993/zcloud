package com.jfatty.zcloud.health.api;

import com.jfatty.zcloud.base.utils.RETResultUtils;
import com.jfatty.zcloud.health.entity.HCSHealthCardInfo;
import com.jfatty.zcloud.health.req.HCSHealthCardInfoReq;
import com.jfatty.zcloud.health.res.DynamicQRCodeRes;
import com.jfatty.zcloud.health.res.HCSHealthCardInfoRes;
import com.jfatty.zcloud.health.res.RegBatHealthCardInfoRes;
import com.jfatty.zcloud.health.res.RegHealthCardInfoRes;
import com.jfatty.zcloud.health.service.HCSHealthCardInfoService;
import com.jfatty.zcloud.health.service.HealthCardStationService;
import com.jfatty.zcloud.health.vo.DynamicQRCodeVO;
import com.jfatty.zcloud.health.vo.HealthCardInfoVO;
import com.jfatty.zcloud.health.vo.ReportHISDataVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述
 *
 * @author jfatty on 2019/12/26
 * @email jfatty@163.com
 */
@Api(tags = "**主**001**电子健康卡平台对接API" ,value = "电子健康卡平台**对接**")
@Slf4j
@RestController
@RequestMapping(value={"/api/healthCardStation"})
public class ApiHealthCardStationController {

    @Autowired
    private HealthCardStationService healthCardStationService ;

    @Autowired
    private HCSHealthCardInfoService hcsHealthCardInfoService ;


    @ApiOperation(value=" 001**** 3.2.2 注册健康卡接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hospitalId", value = "医院ID",dataType = "String",defaultValue = "30646")
    })
    @RequestMapping(value="/registerHealthCard", method=RequestMethod.POST)
    public RETResultUtils<RegHealthCardInfoRes> registerHealthCard(@RequestParam(value = "hospitalId" , defaultValue = "30646" ) String hospitalId, @RequestBody HCSHealthCardInfoReq hcsHealthCardInfoReq){
        try {
            HCSHealthCardInfo hcsHealthCardInfo = new HCSHealthCardInfo();
            BeanUtils.copyProperties(hcsHealthCardInfoReq,hcsHealthCardInfo);

            HCSHealthCardInfo db_HCSHealthCardInfo = hcsHealthCardInfoService.getByIdCardNumber(hcsHealthCardInfoReq.getIdNumber());
            String CID = "" ;
            if (db_HCSHealthCardInfo != null){
                CID = db_HCSHealthCardInfo.getId();
            }else {
                CID = hcsHealthCardInfoService.saveId(hcsHealthCardInfo);
            }
            RegHealthCardInfoRes regHealthCardInfoRes = new RegHealthCardInfoRes();
            HealthCardInfoVO healthCardInfoVO = healthCardStationService.registerHealthCard(hospitalId,hcsHealthCardInfoReq);
            BeanUtils.copyProperties(healthCardInfoVO,regHealthCardInfoRes);
            BeanUtils.copyProperties(healthCardInfoVO,hcsHealthCardInfo);
            hcsHealthCardInfo.setId(CID);
            log.error("==============================开始=========================================");
            hcsHealthCardInfoService.updateById(hcsHealthCardInfo);
            log.error("==============================结束=========================================");
            return new RETResultUtils(regHealthCardInfoRes) ;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }


//    @ApiOperation(value=" 002**** 3.2.3 通过健康卡授权码获取接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "hospitalId", value = "医院ID",dataType = "String",defaultValue = "30646"),
//            @ApiImplicitParam(name = "healthCode", value = "健康卡授权码",dataType = "String",defaultValue = "F9D3F8A308FC0EABC581F5903CAA1094")
//    })
//    @RequestMapping(value="/getHealthCardByHealthCode", method=RequestMethod.GET)
//    public  RETResultUtils<HCSHealthCardInfoRes> getHealthCardByHealthCode(@RequestParam(value = "hospitalId" , defaultValue = "30646" ) String hospitalId , @RequestParam(value = "healthCode" , defaultValue = "F9D3F8A308FC0EABC581F5903CAA1094") String healthCode){
//        try {
//            HCSHealthCardInfoRes hcsHealthCardInfoRes  = new HCSHealthCardInfoRes();
//            HealthCardInfoVO healthCardInfoVO = healthCardStationService.getHealthCardByHealthCode(hospitalId,healthCode);
//            BeanUtils.copyProperties(healthCardInfoVO,hcsHealthCardInfoRes);
//            return new RETResultUtils(hcsHealthCardInfoRes) ;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//
//    }


    @ApiOperation(value=" 003**** 3.2.4 通过健康卡二维码获取接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hospitalId", value = "医院ID",dataType = "String",defaultValue = "30646"),
            @ApiImplicitParam(name = "qrCodeText", value = "二维码文本",dataType = "String",defaultValue = "272EB52D0696FD625B6F3E1A830728532779BEB87520CC83948C50A43F439F58:1")
    })
    @RequestMapping(value="/getHealthCardByQRCode", method=RequestMethod.POST)
    public RETResultUtils<HCSHealthCardInfoRes>  getHealthCardByQRCode(@RequestParam(value = "hospitalId" , defaultValue = "30646" ) String hospitalId ,  @RequestParam(value = "qrCodeText" , defaultValue = "272EB52D0696FD625B6F3E1A830728532779BEB87520CC83948C50A43F439F58:1") String qrCodeText ){

        try {
            HCSHealthCardInfoRes hcsHealthCardInfoRes  = new HCSHealthCardInfoRes();
            HealthCardInfoVO healthCardInfoVO = healthCardStationService.getHealthCardByQRCode(hospitalId,qrCodeText);
            BeanUtils.copyProperties(healthCardInfoVO,hcsHealthCardInfoRes);
            return new RETResultUtils(hcsHealthCardInfoRes) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @ApiOperation(value=" 004**** 3.2.6 绑定健康卡和院内 ID 关系接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hospitalId", value = "医院ID",dataType = "String",defaultValue = "30646"),
            @ApiImplicitParam(name = "patId", value = "院内ID",dataType = "String",defaultValue = "1003243"),
            @ApiImplicitParam(name = "qrCodeText", value = "二维码文本",dataType = "String",defaultValue = "C7DA29345B6DF90A6F5BBEBD73EBE2EDA26F341A6CFEEEB121XXX:1")
    })
    @RequestMapping(value="/bindCardRelation", method=RequestMethod.POST)
    public RETResultUtils<Boolean> bindCardRelation(@RequestParam(value = "hospitalId" , defaultValue = "30646" ) String hospitalId ,  @RequestParam(value = "patId" , defaultValue = "10086") String patId,//
                                                    @RequestParam(value = "qrCodeText" , defaultValue = "C7DA29345B6DF90A6F5BBEBD73EBE2EDA26F341A6CFEEEB121XXX:1") String qrCodeText ){

        try {
            return  new RETResultUtils(healthCardStationService.bindCardRelation(hospitalId,patId,qrCodeText)) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    @ApiOperation(value=" 005**** 3.2.7 用卡数据监测接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hospitalId", value = "医院ID",dataType = "String",defaultValue = "30646")
    })
    @RequestMapping(value="/reportHISData", method=RequestMethod.POST)
    public Object reportHISData(@RequestParam(value = "hospitalId" , defaultValue = "30646" ) String hospitalId ,  @RequestBody ReportHISDataVO reportHISDataVO ){
        try {
            healthCardStationService.reportHISData(hospitalId,reportHISDataVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @ApiOperation(value=" 006**** 3.2.8 获取卡包订单  ID接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hospitalId", value = "医院ID",dataType = "String",defaultValue = "30646"),
            @ApiImplicitParam(name = "qrCodeText", value = "二维码文本",dataType = "String",defaultValue = "C7DA29345B6DF90A6F5BBEBD73EBE2EDA26F341A6CFEEEB121XXX:1")
    })
    @RequestMapping(value="/getOrderIdByOutAppId", method=RequestMethod.GET)
    public RETResultUtils<String> getOrderIdByOutAppId(@RequestParam(value = "hospitalId" , defaultValue = "30646" ) String hospitalId ,   @RequestParam(value = "qrCodeText" , defaultValue = "C7DA29345B6DF90A6F5BBEBD73EBE2EDA26F341A6CFEEEB121XXX:1") String qrCodeText  ){
        try {
            return new RETResultUtils(healthCardStationService.getOrderIdByOutAppId(hospitalId,qrCodeText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @ApiOperation(value=" 007**** 3.2.9 注册批量健康卡接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hospitalId", value = "医院ID",dataType = "String",defaultValue = "30646")
    })
    @RequestMapping(value="/registerBatchHealthCard", method=RequestMethod.POST)
    public RETResultUtils<List<RegBatHealthCardInfoRes>> registerBatchHealthCard(@RequestParam(value = "hospitalId" , defaultValue = "30646" ) String hospitalId ){
        try {
            List<HealthCardInfoVO> healthCardInfos = new ArrayList<HealthCardInfoVO>();
            List<HealthCardInfoVO> list = healthCardStationService.registerBatchHealthCard(hospitalId,healthCardInfos);
            List<RegBatHealthCardInfoRes> regBatHealthCardInfos = new ArrayList<RegBatHealthCardInfoRes>();
            list.forEach(
                    healthCardInfo -> {
                        RegBatHealthCardInfoRes info = new RegBatHealthCardInfoRes();
                        BeanUtils.copyProperties(healthCardInfo,info);
                        regBatHealthCardInfos.add(info);
                    }
            );
            return new RETResultUtils(regBatHealthCardInfos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @ApiOperation(value=" 008**** 3.2.10 获取动态二维码接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hospitalId", value = "医院ID",dataType = "String",defaultValue = "30646"),
            @ApiImplicitParam(name = "healthCardId", value = "健康卡ID",dataType = "String",defaultValue = "272EB52D0696FD625B6F3E1A830728532779BEB87520CC83948C50A43F439F58"),
            @ApiImplicitParam(name = "idType", value = "证件类型",dataType = "String",defaultValue = "01"),
            @ApiImplicitParam(name = "idNumber", value = "证件号码",dataType = "String",defaultValue = "422801199509094611")
    })
    @RequestMapping(value="/getDynamicQRCode", method=RequestMethod.GET)
    public RETResultUtils<DynamicQRCodeRes>  getDynamicQRCode(@RequestParam(value = "hospitalId" , defaultValue = "30646" ) String hospitalId , @RequestParam(value = "healthCardId" , defaultValue = "272EB52D0696FD625B6F3E1A830728532779BEB87520CC83948C50A43F439F58") String healthCardId ,
                                                              @RequestParam(value = "idType" , defaultValue = "01") String idType , @RequestParam(value = "idNumber" , defaultValue = "422801199509094611") String idNumber ){
        try {
            DynamicQRCodeRes dynamicQRCodeRes = new DynamicQRCodeRes();
            DynamicQRCodeVO dynamicQRCodeVO =  healthCardStationService.getDynamicQRCode(hospitalId,healthCardId,idType,idNumber);
            BeanUtils.copyProperties(dynamicQRCodeVO,dynamicQRCodeRes);
            return new RETResultUtils(dynamicQRCodeRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
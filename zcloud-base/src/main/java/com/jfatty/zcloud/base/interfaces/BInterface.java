package com.jfatty.zcloud.base.interfaces;

import com.jfatty.zcloud.base.utils.RELResultUtils;
import com.jfatty.zcloud.base.utils.ResultUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 描述
 *
 * @author jfatty on 2019/11/5
 * @email jfatty@163.com
 */
public interface BInterface<T> {

    //列表 增删改查 其余接口子类自定义

    @RequestMapping(value={"/table/list"},method = RequestMethod.POST)
    RELResultUtils<T> table(@RequestBody Map<String,Object> params) ;


    @RequestMapping(value={"/table/list"},method = RequestMethod.GET )
    RELResultUtils<T> table(@RequestParam(value = "v" , defaultValue = "20191101") String v ,
                            @RequestParam(value = "pageIndex" , defaultValue = "1" ) Integer pageIndex ,
                            @RequestParam(value = "pageSize" , defaultValue = "10") Integer pageSize) ;


    @RequestMapping(value={"/list"},method=RequestMethod.GET)
    ResultUtils list() ;

    @RequestMapping(value={"/list"},method=RequestMethod.POST)
    List<T>  list(@RequestParam(value = "v" , defaultValue = "20191101") Long v) ;

    @RequestMapping(value={"/save"},method=RequestMethod.POST)
    ResultUtils save(@RequestBody T entity) ;

    @RequestMapping(value={"/edit"},method=RequestMethod.GET)
    ResultUtils view(@RequestParam(value = "id" , defaultValue = "AQAQAQ") String id ) ;

    @RequestMapping(value={"/edit"},method=RequestMethod.POST)
    ResultUtils edit(@RequestBody T entity) ;

    @RequestMapping(value={"/delete"},method=RequestMethod.POST)
    ResultUtils delete(@RequestBody Map<String,Object> params) ;
}

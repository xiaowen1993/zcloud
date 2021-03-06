package com.jfatty.zcloud.system.req;

import com.jfatty.zcloud.system.dto.OrgDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 描述
 *
 * @author jfatty on 2019/12/16
 * @email jfatty@163.com
 */
@Data
@ApiModel(description = "组织机构请求实体")
public class OrgReq  extends OrgDTO<OrgReq> {

}

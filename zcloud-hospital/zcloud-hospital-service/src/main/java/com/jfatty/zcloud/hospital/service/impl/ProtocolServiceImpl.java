package com.jfatty.zcloud.hospital.service.impl;


import com.jfatty.zcloud.hospital.entity.Protocol;
import com.jfatty.zcloud.hospital.mapper.ProtocolMapper;
import com.jfatty.zcloud.hospital.service.ProtocolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 协议或用户需知表 服务实现类
 * </p>
 *
 * @author jfatty
 * @since 2019-12-12
 */
@Slf4j
@Service
public class ProtocolServiceImpl extends BaseHospitalServiceImpl<Protocol, ProtocolMapper> implements ProtocolService {

    private ProtocolMapper protocolMapper ;

    @Autowired
    public void setProtocolMapper(ProtocolMapper protocolMapper) {
        super.setBaseMapper(protocolMapper);
        this.protocolMapper = protocolMapper;
    }

    @Override
    public List<Protocol> getByDiffs(String appId, String version, String opcode) {
        return protocolMapper.getByDiffs(version,opcode);
    }

    @Override
    public List<Protocol> getProtocol(String appId, String version, String pageId) {
        return protocolMapper.getProtocol(appId,version,pageId);
    }
}

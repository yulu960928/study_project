package com.stat.demo;

import com.yupaopao.bixin.playmate.statistics.sdk.converter.converter.StandardIdentificationConverter;
import com.yupaopao.bixin.playmate.statistics.sdk.converter.dto.StandardIdentificationDto;
import com.yupaopao.bixin.playmate.statistics.sdk.converter.util.ContextUtil;

import java.util.Map;

/**
 * @author liwei
 * @date 2021/8/19
 **/
public class DemoIdentificationConverter implements StandardIdentificationConverter {

    @Override
    public StandardIdentificationDto convert(Map<String, Object> map) {
        StandardIdentificationDto standardIdentificationDto=new StandardIdentificationDto();
        standardIdentificationDto.setBizId((String)map.get("bizId"));
        standardIdentificationDto.setBizTime(ContextUtil.getDateFromContext(map,"orderCreateTime"));
        return standardIdentificationDto;
    }

}

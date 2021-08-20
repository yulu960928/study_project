package com.stat.demo;

import com.yupaopao.bixin.playmate.statistics.sdk.converter.converter.StandardStatConverter;
import com.yupaopao.bixin.playmate.statistics.sdk.converter.dto.StandardStatDto;
import com.yupaopao.bixin.playmate.statistics.sdk.converter.enums.Action;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author liwei
 * @date 2021/8/19
 **/
@Slf4j
public class DemoStatConverter implements StandardStatConverter {

    @Override
    public StandardStatDto convert(Map<String, Object> map) {
        System.out.println(map);
        StandardStatDto standardStatDto=new StandardStatDto();
        standardStatDto.setAction(Action.PLUS);
        standardStatDto.setCountValue((Long)map.get("bizType"));
        standardStatDto.setDimensionVal((String)map.get("orderId"));

        return standardStatDto;
    }

}

package com.stat.demo.order;

import com.yupaopao.bixin.playmate.statistics.sdk.converter.converter.StandardStatConverter;
import com.yupaopao.bixin.playmate.statistics.sdk.converter.dto.StandardStatDto;
import com.yupaopao.bixin.playmate.statistics.sdk.converter.enums.Action;

import java.util.Map;

/**
 * @author liwei
 * @date 2021/8/19
 * 统计买卖家id  每天完成的数量
 **/

public class OrderBuyllerCompletedStatConverter implements StandardStatConverter {

    @Override
    public StandardStatDto convert(Map<String, Object> map) {
        System.out.println(map);
        StandardStatDto standardStatDto=new StandardStatDto();
        standardStatDto.setAction(Action.PLUS);
        standardStatDto.setCountValue(1L);
        standardStatDto.setDimensionVal((String)map.get("buyerId"));

        return standardStatDto;
    }

}

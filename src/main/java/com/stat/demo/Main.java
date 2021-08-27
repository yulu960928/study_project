package com.stat.demo;

import com.alibaba.fastjson.JSON;
import com.yupaopao.bixin.playmate.statistics.api.source.AddSourceMetricsMappingRequest;
import com.yupaopao.bixin.playmate.statistics.api.source.AddSourceRequest;
import com.yupaopao.bixin.playmate.statistics.api.source.UpdateSourceMetricsConverterRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liwei
 * @date 2021/8/26
 **/
public class Main {

    public static void main(String[] args) {
        updateSourceMetricsConvert();
    }

    public static void addSource(){
        AddSourceRequest addSourceRequest = new AddSourceRequest();
        addSourceRequest.setSourceCode("ORDER_BASE_COMPLETE");
        addSourceRequest.setSourceName("订单完成");
        addSourceRequest.setTopic("ORDER_BASE_COMPLETE");
        addSourceRequest.setNameSpace("middleware.kafka-batch");
        addSourceRequest.setConvertCode("package com.stat.demo.order;\n" +
                "\n" +
                "import com.yupaopao.bixin.playmate.statistics.sdk.converter.converter.StandardIdentificationConverter;\n" +
                "import com.yupaopao.bixin.playmate.statistics.sdk.converter.dto.StandardIdentificationDto;\n" +
                "import com.yupaopao.bixin.playmate.statistics.sdk.converter.util.ContextUtil;\n" +
                "\n" +
                "import java.util.Map;\n" +
                "\n" +
                "/**\n" +
                " * @author liwei\n" +
                " * @date 2021/8/19\n" +
                " **/\n" +
                "public class OrderCompletedIdConverter implements StandardIdentificationConverter {\n" +
                "\n" +
                "    @Override\n" +
                "    public StandardIdentificationDto convert(Map<String, Object> map) {\n" +
                "        StandardIdentificationDto standardIdentificationDto=new StandardIdentificationDto();\n" +
                "        standardIdentificationDto.setBizId((String)map.get(\"orderId\"));\n" +
                "        standardIdentificationDto.setBizTime(ContextUtil.getDateFromContext(map,\"orderCreateTime\"));\n" +
                "        return standardIdentificationDto;\n" +
                "    }\n" +
                "\n" +
                "}\n");

        List<AddSourceMetricsMappingRequest> metricsMappingList = new ArrayList();
        AddSourceMetricsMappingRequest addSourceMetricsMappingRequest=new AddSourceMetricsMappingRequest();
        addSourceMetricsMappingRequest.setMetricsCode("completedNum");
        addSourceMetricsMappingRequest.setDimensionCode("buyerId");
        addSourceMetricsMappingRequest.setSourceCode("ORDER_BASE_COMPLETE");
        addSourceMetricsMappingRequest.setConvertCode("package com.stat.demo.order;\n" +
                "\n" +
                "import com.yupaopao.bixin.playmate.statistics.sdk.converter.converter.StandardStatConverter;\n" +
                "import com.yupaopao.bixin.playmate.statistics.sdk.converter.dto.StandardStatDto;\n" +
                "import com.yupaopao.bixin.playmate.statistics.sdk.converter.enums.Action;\n" +
                "\n" +
                "import java.util.Map;\n" +
                "\n" +
                "/**\n" +
                " * @author liwei\n" +
                " * @date 2021/8/19\n" +
                " * 统计买卖家id  每天完成的数量\n" +
                " **/\n" +
                "\n" +
                "public class OrderBuyllerCompletedStatConverter implements StandardStatConverter {\n" +
                "\n" +
                "    @Override\n" +
                "    public StandardStatDto convert(Map<String, Object> map) {\n" +
                "        System.out.println(map);\n" +
                "        StandardStatDto standardStatDto=new StandardStatDto();\n" +
                "        standardStatDto.setAction(Action.PLUS);\n" +
                "        standardStatDto.setCountValue(1L);\n" +
                "        standardStatDto.setDimensionVal((String)map.get(\"buyerId\"));\n" +
                "\n" +
                "        return standardStatDto;\n" +
                "    }\n" +
                "\n" +
                "}\n");

        metricsMappingList.add(addSourceMetricsMappingRequest);
        AddSourceMetricsMappingRequest addSourceMetricsMappingRequest2=new AddSourceMetricsMappingRequest();
        addSourceMetricsMappingRequest2.setMetricsCode("completedNum");
        addSourceMetricsMappingRequest2.setDimensionCode("sellerId");
        addSourceMetricsMappingRequest2.setSourceCode("ORDER_BASE_COMPLETE");
        addSourceMetricsMappingRequest2.setConvertCode("package com.stat.demo.order;\n" +
                "\n" +
                "import com.yupaopao.bixin.playmate.statistics.sdk.converter.converter.StandardStatConverter;\n" +
                "import com.yupaopao.bixin.playmate.statistics.sdk.converter.dto.StandardStatDto;\n" +
                "import com.yupaopao.bixin.playmate.statistics.sdk.converter.enums.Action;\n" +
                "\n" +
                "import java.util.Map;\n" +
                "\n" +
                "/**\n" +
                " * @author liwei\n" +
                " * @date 2021/8/19\n" +
                " * 统计买卖家id  每天完成的数量\n" +
                " **/\n" +
                "\n" +
                "public class OrderBuyllerCompletedStatConverter implements StandardStatConverter {\n" +
                "\n" +
                "    @Override\n" +
                "    public StandardStatDto convert(Map<String, Object> map) {\n" +
                "        System.out.println(map);\n" +
                "        StandardStatDto standardStatDto=new StandardStatDto();\n" +
                "        standardStatDto.setAction(Action.PLUS);\n" +
                "        standardStatDto.setCountValue(1L);\n" +
                "        standardStatDto.setDimensionVal((String)map.get(\"buyerId\"));\n" +
                "\n" +
                "        return standardStatDto;\n" +
                "    }\n" +
                "\n" +
                "}\n");
        metricsMappingList.add(addSourceMetricsMappingRequest2);
        addSourceRequest.setMetricsMappingList(metricsMappingList);
        System.out.println(JSON.toJSON(addSourceRequest));
    }


    public static void updateSourceMetricsConvert(){
        UpdateSourceMetricsConverterRequest updateSourceMetricsConverterRequest = new UpdateSourceMetricsConverterRequest();
        updateSourceMetricsConverterRequest.setId(3L);
        updateSourceMetricsConverterRequest.setConverter("package com.stat.demo.order;\n" +
                "\n" +
                "import com.yupaopao.bixin.playmate.statistics.sdk.converter.converter.StandardStatConverter;\n" +
                "import com.yupaopao.bixin.playmate.statistics.sdk.converter.dto.StandardStatDto;\n" +
                "import com.yupaopao.bixin.playmate.statistics.sdk.converter.enums.Action;\n" +
                "\n" +
                "import java.util.Map;\n" +
                "\n" +
                "/**\n" +
                " * @author liwei\n" +
                " * @date 2021/8/19\n" +
                " * 统计买卖家id  每天完成的数量\n" +
                " **/\n" +
                "\n" +
                "public class OrderSellerCompletedStatConverter implements StandardStatConverter {\n" +
                "\n" +
                "    @Override\n" +
                "    public StandardStatDto convert(Map<String, Object> map) {\n" +
                "        System.out.println(map);\n" +
                "        StandardStatDto standardStatDto=new StandardStatDto();\n" +
                "        standardStatDto.setAction(Action.PLUS);\n" +
                "        standardStatDto.setCountValue(1L);\n" +
                "        standardStatDto.setDimensionVal((String)map.get(\"sellerId\"));\n" +
                "\n" +
                "        return standardStatDto;\n" +
                "    }\n" +
                "\n" +
                "}\n");
        System.out.println(JSON.toJSON(updateSourceMetricsConverterRequest));
    }
}

package com.stat.demo.god.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author liwei
 * @date 2021/8/26
 **/
public class Main {

    public static void main(String[] args) {
        List<MessageDto> messageDtos = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            MessageDto messageDto = new MessageDto();
            messageDto.setMessageId(Long.valueOf(i));
            messageDto.setBizId(String.valueOf(i));
            messageDto.setBizTime(new Date());
            messageDto.setSourceCode("ORDER_BASE_SUBMIT_DELIVERY_ATTACHMENT_SUCCESS");
        }

        String msg = "{\\\"contractId\\\":null,\\\"orderId\\\":\\\"20210826145700010000000500726742\\\",\\\"bizType\\\":0,\\\"bizId\\\":\\\"\\\",\\\"activityType\\\":0,\\\"orderItemType\\\":0,\\\"orderType\\\":1,\\\"orderSubType\\\":5,\\\"buyerId\\\":\\\"202240904086410166\\\",\\\"appId\\\":10,\\\"sellerId\\\":\\\"202370717507090092\\\",\\\"sellerType\\\":1,\\\"orderItemKafkaDTOList\\\":[{\\\"sellerId\\\":\\\"202370717507090092\\\",\\\"catId\\\":\\\"b2f36bbf94624eda70367bb5146e8bcf\\\",\\\"spuId\\\":\\\"b2f36bbf94624eda70367bb5146e8bcf\\\",\\\"skuId\\\":\\\"9\\\",\\\"itemExtVO\\\":{\\\"itemSerialVOList\\\":null}}],\\\"orderCreateTime\\\":1629961026000,\\\"sourceKafkaDTO\\\":{\\\"operator\\\":\\\"\\\",\\\"deviceId\\\":\\\"20200723191017e094a359067c2ad818171364c358d02901cd005fa684d1cf\\\",\\\"ip\\\":\\\"58.33.50.234\\\",\\\"clientVersion\\\":\\\"8.5.0\\\",\\\"platform\\\":7,\\\"operationTime\\\":1629961046657},\\\"customExt\\\":\\\"{}\\\",\\\"tagKeys\\\":\\\"\\\",\\\"deliveryAttachmentId\\\":\\\"c4766b73a5d741c5b37665d949099d3f\\\",\\\"deliveryAttachment\\\":\\\"[\\\\\\\"bx-user/8a4e5980-71cb-4c95-9712-f3cbdcb33cbe.jpg\\\\\\\"]\\\",\\\"deliveryCreateTime\\\":1629961046621}";
        Map<String, Object> sourceData = JSON.parseObject(msg, new TypeReference<Map<String, Object>>() {});
        System.out.println(sourceData);
    }


}

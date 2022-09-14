package com.stat.demo;

import com.alibaba.fastjson.JSON;
import com.stat.demo.bixin.RoomPendantConfigManager;
import com.stat.demo.bixin.dto.RoomPendantResponse;
import com.stat.demo.handler.AppkitHandler;
import com.yupaopao.platform.appkit.sdk.base.client.AppkitResponse;
import com.yupaopao.platform.appkit.sdk.base.datatypes.AppkitConfig;
import com.yupaopao.platform.appkit.sdk.base.datatypes.AppkitContent;
import com.yupaopao.platform.appkit.sdk.client.client.AppkitRequest;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

public class RoomPendantConfigManagerTest extends BaseTest {

    @MockBean
    private AppkitHandler appkitHandler;
//    @MockBean
//    private AppkitClient appkitClient;

    @Resource
    private RoomPendantConfigManager roomPendantConfigManager;

    @Test
    public void getPendantList() {
        mock();
        RoomPendantResponse pendantList = roomPendantConfigManager.getPendantList(1);
        System.out.println(JSON.toJSONString(pendantList));
    }

    private void mock() {
        AppkitResponse appkitResponse = new AppkitResponse();
        appkitResponse.setStatusCode(200);
        appkitResponse.setMessage("");

        AppkitConfig appkitConfig = new AppkitConfig();
        appkitResponse.setData(Lists.newArrayList(appkitConfig));

        appkitConfig.setExtJson("{\n" +
                "      \"aspectRatio\": 1,\n" +
                "      \"toTop\": 20,\n" +
                "      \"eleGroup\": 0,\n" +
                "      \"scene\": \"game_room_pendant\",\n" +
                "      \"eleOrder\": 6,\n" +
                "      \"toLeft\": 70,\n" +
                "      \"contents\": [\n" +
                "      ],\n" +
                "      \"configId\": 100422,\n" +
                "      \"width\": 20,\n" +
                "      \"showCloseButton\": 1,\n" +
                "      \"carouselInterval\": 0,\n" +
                "      \"eleName\": \"\",\n" +
                "      \"eleDesc\": \"\"\n" +
                "    }");

        String contentJson = "{\n" +
                "          \"scheme\": \"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fp8.itc.cn%2Fq_70%2Fimages01%2F20210825%2F008504e088694a2c850755b587109dbf.jpeg&refer=http%3A%2F%2Fp8.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1663493439&t=662b07e6f3c8f440a2f995316891437d\",\n" +
                "          \"createTime\": \"2022-08-19 17:31:31\",\n" +
                "          \"contentId\": 14340,\n" +
                "          \"updateTime\": \"2022-08-19 17:31:31\",\n" +
                "          \"type\": 0,\n" +
                "          \"url\": \"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fp8.itc.cn%2Fq_70%2Fimages01%2F20210825%2F008504e088694a2c850755b587109dbf.jpeg&refer=http%3A%2F%2Fp8.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1663493439&t=662b07e6f3c8f440a2f995316891437d\",\n" +
                "          \"order\": 0\n" +
                "        }";
        AppkitContent appkitContent = new AppkitContent(100422, 14340, contentJson, "{}", null, null);
        appkitContent.initTransient();
        appkitConfig.setContents(Lists.newArrayList(appkitContent));

        Mockito.when(appkitHandler.query(Mockito.any(AppkitRequest.class))).thenReturn(appkitResponse);
    }

}
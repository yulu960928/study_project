package com.stat.demo;

import com.alibaba.fastjson.JSON;
import com.yupaopao.platform.appkit.sdk.base.datatypes.AppkitConfig;
import org.junit.Test;

import java.util.List;

public class ResponseParseTest {

    @Test
    public void testParse() {
        List<AppkitConfig> configs = JSON.parseArray(json, AppkitConfig.class);
        System.out.println(configs);
    }

    String json = "[\n" +
            "    {\n" +
            "      \"aspectRatio\": 1,\n" +
            "      \"toTop\": 20,\n" +
            "      \"eleGroup\": 0,\n" +
            "      \"scene\": \"game_room_pendant\",\n" +
            "      \"eleOrder\": 6,\n" +
            "      \"toLeft\": 70,\n" +
            "      \"contents\": [\n" +
            "        {\n" +
            "          \"scheme\": \"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fp8.itc.cn%2Fq_70%2Fimages01%2F20210825%2F008504e088694a2c850755b587109dbf.jpeg&refer=http%3A%2F%2Fp8.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1663493439&t=662b07e6f3c8f440a2f995316891437d\",\n" +
            "          \"createTime\": \"2022-08-19 17:31:31\",\n" +
            "          \"contentId\": 14340,\n" +
            "          \"updateTime\": \"2022-08-19 17:31:31\",\n" +
            "          \"type\": 0,\n" +
            "          \"url\": \"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fp8.itc.cn%2Fq_70%2Fimages01%2F20210825%2F008504e088694a2c850755b587109dbf.jpeg&refer=http%3A%2F%2Fp8.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1663493439&t=662b07e6f3c8f440a2f995316891437d\",\n" +
            "          \"order\": 0\n" +
            "        }\n" +
            "      ],\n" +
            "      \"configId\": 100422,\n" +
            "      \"width\": 20,\n" +
            "      \"showCloseButton\": 1,\n" +
            "      \"carouselInterval\": 0,\n" +
            "      \"eleName\": \"\",\n" +
            "      \"eleDesc\": \"\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"aspectRatio\": 2,\n" +
            "      \"toTop\": 70,\n" +
            "      \"eleGroup\": 0,\n" +
            "      \"scene\": \"game_room_pendant\",\n" +
            "      \"eleOrder\": 8,\n" +
            "      \"toLeft\": 70,\n" +
            "      \"contents\": [\n" +
            "        {\n" +
            "          \"scheme\": \"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202003%2F18%2F20200318131532_enika.thumb.400_0.jpg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1663493743&t=dd486d17652f5e94d1f2a79136ab1f91\",\n" +
            "          \"createTime\": \"2022-08-19 17:55:23\",\n" +
            "          \"contentId\": 14341,\n" +
            "          \"updateTime\": \"2022-08-19 17:55:23\",\n" +
            "          \"type\": 0,\n" +
            "          \"url\": \"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202003%2F18%2F20200318131532_enika.thumb.400_0.jpg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1663493743&t=dd486d17652f5e94d1f2a79136ab1f91\",\n" +
            "          \"order\": 0\n" +
            "        },\n" +
            "        {\n" +
            "          \"scheme\": \"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202003%2F18%2F20200318131532_enika.thumb.400_0.jpg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1663493743&t=dd486d17652f5e94d1f2a79136ab1f91\",\n" +
            "          \"createTime\": \"2022-08-21 14:29:13\",\n" +
            "          \"contentId\": 14363,\n" +
            "          \"updateTime\": \"2022-08-21 14:29:13\",\n" +
            "          \"type\": 0,\n" +
            "          \"url\": \"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202003%2F18%2F20200318131532_enika.thumb.400_0.jpg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1663493743&t=dd486d17652f5e94d1f2a79136ab1f91\",\n" +
            "          \"order\": 0\n" +
            "        }\n" +
            "      ],\n" +
            "      \"configId\": 100423,\n" +
            "      \"width\": 1,\n" +
            "      \"showCloseButton\": 1,\n" +
            "      \"carouselInterval\": 4,\n" +
            "      \"eleName\": \"\",\n" +
            "      \"eleDesc\": \"\"\n" +
            "    }\n" +
            "  ]";
}

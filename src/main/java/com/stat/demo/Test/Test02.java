package com.stat.demo.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.apache.commons.lang3.time.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

public class Test02 {


    public static void main(String[] args) throws IOException, ParseException {

        ObjectMapper objectMapper = new ObjectMapper();
        String s = "request:{\"appId\":10,\"fromUid\":212520752501095984,\"toUid\":200290068306411451} result:{\"dailyReceivedUserLimit\":{\"limit\":2147483647,\"limitToast\":\"太多人找ta了，请明天再来吧\",\"normalCount\":0,\"strangeCount\":6,\"totalCount\":6},\"dailySentUserLimit\":{\"limit\":2147483647,\"limitToast\":\"超过今日找人上限，明天再来吧\",\"normalCount\":0,\"strangeCount\":1,\"totalCount\":1},\"fromUserType\":0,\"recentlyReceivedUserLimit\":{\"limit\":2147483647,\"limitToast\":\"太多人找ta了，请稍后再来吧\",\"normalCount\":0,\"strangeCount\":3,\"totalCount\":3},\"stranger\":false,\"toUserType\":0}\n";

        String format = "im-user-limit-statistics request:{} result:{}";

        //Map map = objectMapper.readValue(s, Map.class);
        //System.out.println(map.toString());

//        Temp temp = JSONObject.parseObject(s, Temp.class);
//        System.out.println(temp.getRequest());
//        System.out.println(temp.getResult());

        Date date = DateUtils.parseDate("2022-05-22 10:05", "yyyy-MM-dd HH:mm");
        System.out.println(date.toString());




    }

}

@Data
class Temp{
    String request;
    String result;
}

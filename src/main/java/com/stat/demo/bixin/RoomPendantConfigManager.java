package com.stat.demo.bixin;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.stat.demo.bixin.dto.RoomPendantDetailDTO;
import com.stat.demo.bixin.dto.RoomPendantResponse;
import com.stat.demo.bixin.dto.RoomPendantStyleDTO;
import com.stat.demo.handler.AppkitHandler;
import com.yupaopao.platform.appkit.sdk.base.client.AppkitResponse;
import com.yupaopao.platform.appkit.sdk.base.datatypes.AppkitConfig;
import com.yupaopao.platform.appkit.sdk.base.datatypes.AppkitContent;
import com.yupaopao.platform.appkit.sdk.client.client.AppkitRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yulu
 */
@Slf4j
@Component
public class RoomPendantConfigManager {

    @Resource
    private AppkitHandler appkitHandler;
    /**
     * 游戏房挂饰 appKit Key
     */
    private static final String gameRoomPendant = "game_room_pendant";

    /**
     * 获取游戏房内资源位挂件区
     *
     * @return
     */
    public RoomPendantResponse getPendantList(Integer  type) {

        AppkitRequest appkitRequest = new AppkitRequest();
        appkitRequest.setSceneKey(gameRoomPendant);

        //appkitRequest.addField("platform", platform);
        AppkitResponse response = appkitHandler.query(appkitRequest);
        List<RoomPendantStyleDTO> resultList = Lists.newArrayList();
        if (response.isSuccess()) {
            List<AppkitConfig> configs = response.getData();
            configs.forEach(config->{
                RoomPendantStyleDTO roomPendantStyleDTO = JSON.parseObject(config.getExtJson(), RoomPendantStyleDTO.class);
                List<RoomPendantDetailDTO> pendantDetailList = Lists.newArrayList();
                config.getContents().forEach(content -> {
                    RoomPendantDetailDTO detailDTO = JSON.parseObject(content.getContent(), RoomPendantDetailDTO.class);
                    pendantDetailList.add(detailDTO);
                });
                roomPendantStyleDTO.setPendantDetailList(pendantDetailList);
                resultList.add(roomPendantStyleDTO);
            });
            if (CollectionUtils.isNotEmpty(configs)) {
                for (AppkitConfig config : configs) {

                    config.getExtString("xxx-key"); // 取外层"xxx-key"的值
                    List<AppkitContent> contents = config.getContents();
                    if (CollectionUtils.isNotEmpty(contents)) {
                        continue;
                    }
                    for (AppkitContent content : contents) {
                        content.getContentString("yyy-key"); // 取里层"yyy-key"的值
                    }
                }
            }
        }
        log.info("resultList {}", JSON.toJSONString(resultList));


//        if (!appkitResponse.isSuccess() || CollectionUtils.isEmpty(appkitResponse.getData())) {
//            return null;
//        }

        return null;
    }
}

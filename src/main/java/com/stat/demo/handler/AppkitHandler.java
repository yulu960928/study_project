package com.stat.demo.handler;

import com.yupaopao.platform.appkit.sdk.base.client.AppkitResponse;
import com.yupaopao.platform.appkit.sdk.client.client.AppkitClient;
import com.yupaopao.platform.appkit.sdk.client.client.AppkitRequest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author yulu
 */
@Component
public class AppkitHandler {

//    @Resource
//    private AppkitClient appkitClient;

    public AppkitResponse query(AppkitRequest request) {
//        return appkitClient.query(request);
        return new AppkitResponse();
    }
}

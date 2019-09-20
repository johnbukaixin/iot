package com.ptl.push;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * created by panta on 2019/9/18.
 *
 * @author panta
 */
@Component
public class JpushClientInstanceConfig {

    @Autowired
    private JpushConfig jpushConfig;

    @Bean
    public JPushClient JpushClient() {
        //todo:此处配置需要修改
        ClientConfig config = ClientConfig.getInstance();
        // development env
        config.setApnsProduction(false);
        // one day
        config.setTimeToLive(60 * 60 * 24);
        return new JPushClient(jpushConfig.getMasterSecret(), jpushConfig.getAppKey(), null, config);
    }
}

package com.ptl.push;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * created by panta on 2019/9/18.
 *
 * @author panta
 */
@Component
public class Test implements ApplicationRunner {
    @Resource
    private JpushUtil jpushUtil;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        ResponseNoticeMessage message = new ResponseNoticeMessage();
        message.setContent("通知test");
        message.setTitle("通知");
        jpushUtil.sendPushAllMessage(message);

        jpushUtil.testSendPush();
    }
}

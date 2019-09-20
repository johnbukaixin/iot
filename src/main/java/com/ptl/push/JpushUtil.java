package com.ptl.push;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * created by panta on 2019/9/18.
 *
 * @author panta
 */
@Component
public class JpushUtil {

    private Logger logger = LoggerFactory.getLogger(JpushUtil.class);
    @Autowired
    private JPushClient jpushClient;


    /**
     * 推送所有平台 用于广播消息  用于管理员使用
     *
     * @param responseNoticeMessage 消息
     */
    public void sendPushAllMessage(ResponseNoticeMessage responseNoticeMessage) {
        //生成推送的内容
        PushPayload payload = JPushClientPC.buildPushObject_all_message(responseNoticeMessage);
        PushResult pushResult = sendPush(payload);
        logger.info(JSON.toJSONString(pushResult));
    }


    /**
     * 根据别名推送所有平台
     * 一次推送最多 1000 个。
     *
     * @param userNames             别名
     * @param responseNoticeMessage 消息
     */
    public void sendPushAliasAllMessage(List<String> userNames, ResponseNoticeMessage responseNoticeMessage) {
        //生成推送的内容
        PushPayload payload = JPushClientPC.buildPushObject_all_alias_message(userNames, responseNoticeMessage);
        sendPush(payload);
    }


    /**
     * 根据别名推送所有平台
     *
     * @param userName              别名
     * @param responseNoticeMessage 消息
     */
    public void sendPushAliasAllMessage(String userName, ResponseNoticeMessage responseNoticeMessage) {
        //生成推送的内容
        PushPayload payload = JPushClientPC.buildPushObject_all_alias_message(userName, responseNoticeMessage);
        sendPush(payload);
    }

    /**
     * 根据标签推送所有平台
     * 一次推送最多 20 个。
     *
     * @param tags                  标签名
     * @param responseNoticeMessage 消息
     */
    public void sendPushTagsAllMessage(List<String> tags, ResponseNoticeMessage responseNoticeMessage) {
        //生成推送的内容
        PushPayload payload = JPushClientPC.buildPushObject_all_tags_message(tags, responseNoticeMessage);
        sendPush(payload);
    }


    /**
     * 根据标签推送所有平台
     *
     * @param tag                   标签名
     * @param responseNoticeMessage 消息
     */
    public void sendPushTagsAllMessage(String tag, ResponseNoticeMessage responseNoticeMessage) {
        //生成推送的内容
        PushPayload payload = JPushClientPC.buildPushObject_all_tag_message(tag, responseNoticeMessage);
        sendPush(payload);
    }

    /**
     * 发送
     *
     * @param payload
     * @return
     */
    private PushResult sendPush(PushPayload payload) {
        try {
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();

            return result;
        } catch (Exception e) {
            logger.info("sendPushAllAlert ： HTTP Status -=- {}", e.getMessage());

        }
        return null;
    }
    public  void testSendPush() {
        ClientConfig clientConfig = ClientConfig.getInstance();
//        String authCode = ServiceHelper.getBasicAuthorization(APP_KEY, MASTER_SECRET);
        // Here you can use NativeHttpClient or NettyHttpClient or ApacheHttpClient.
        // Call setHttpClient to set httpClient,
        // If you don't invoke this method, default httpClient will use NativeHttpClient.

//        ApacheHttpClient httpClient = new ApacheHttpClient(authCode, null, clientConfig);
//        NettyHttpClient httpClient =new NettyHttpClient(authCode, null, clientConfig);
//        jpushClient.getPushClient().setHttpClient(httpClient);
        final PushPayload payload = JPushClientPC.buildPushObject_android_and_ios();
//        // For push, all you need do is to build PushPayload object.
//        PushPayload payload = buildPushObject_all_alias_alert();
        try {
            PushResult result = jpushClient.sendPush(payload);
            logger.info("Got result - " + result);
            System.out.println(result);
            // 如果使用 NettyHttpClient，需要手动调用 close 方法退出进程
            // If uses NettyHttpClient, call close when finished sending request, otherwise process will not exit.
            // jpushClient.close();
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
            logger.error("Sendno: " + payload.getSendno());

        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
            logger.error("Sendno: " + payload.getSendno());
        }
    }
}

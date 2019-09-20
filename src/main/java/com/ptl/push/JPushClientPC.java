package com.ptl.push;

import cn.jiguang.common.ServiceHelper;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.*;

/**
 * created by panta on 2019/9/18.
 *
 * @author panta
 */
public class JPushClientPC {
    public static final String TITLE = "Test from API example";
    public static final String ALERT = "Test from API Example - alert";
    public static final String MSG_CONTENT = "Test from API Example - msgContent";
    public static final String REGISTRATION_ID = "0900e8d85ef";
    public static final String TAG = "tag_api";
    /**
     * 发送所有的消息  全部人
     *
     * @param responseNoticeMessage
     * @return
     */
    public static PushPayload buildPushObject_all_message(ResponseNoticeMessage responseNoticeMessage) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())//设置接受的平台
                .setAudience(Audience.all())//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
                //.setNotification(Notification.alert(JSON.toJSONString(responseNoticeMessage)))
                .setMessage(Message.content(JSON.toJSONString(responseNoticeMessage)))  //内部消息不显示，类似web socket
                .build();
    }

    /**
     * 根据别名集合 推送
     *
     * @param userNames             别名List
     * @param responseNoticeMessage 消息
     * @return
     */
    public static PushPayload buildPushObject_all_alias_message(List<String> userNames, ResponseNoticeMessage responseNoticeMessage) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())//设置接受的平台
                .setAudience(Audience.alias(userNames))//
                //.setNotification(Notification.alert(JSON.toJSONString(responseNoticeMessage)))
                .setMessage(Message.content(JSON.toJSONString(responseNoticeMessage)))  //内部消息不显示，类似web socket
                .build();
    }

    /**
     * 根据别名 推送
     *
     * @param userName              别名
     * @param responseNoticeMessage 消息
     * @return
     */
    public static PushPayload buildPushObject_all_alias_message(String userName, ResponseNoticeMessage responseNoticeMessage) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())//设置接受的平台
                .setAudience(Audience.alias(userName))//
                //.setNotification(Notification.alert(JSON.toJSONString(responseNoticeMessage)))
                .setMessage(Message.content(JSON.toJSONString(responseNoticeMessage)))  //内部消息不显示，类似web socket
                .build();
    }

    /**
     * 根据标签集合推送
     *
     * @param tags                  标签List
     * @param responseNoticeMessage 消息
     * @return
     */
    public static PushPayload buildPushObject_all_tags_message(List<String> tags, ResponseNoticeMessage responseNoticeMessage) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())//设置接受的平台
                .setAudience(Audience.tag(tags))//
                //.setNotification(Notification.alert(JSON.toJSONString(responseNoticeMessage)))
                .setMessage(Message.content(JSON.toJSONString(responseNoticeMessage)))  //内部消息不显示，类似web socket
                .build();
    }

    /**
     * 根据标签推送
     *
     * @param tag                   标签
     * @param responseNoticeMessage 消息
     * @return
     */
    public static PushPayload buildPushObject_all_tag_message(String tag, ResponseNoticeMessage responseNoticeMessage) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())//设置接受的平台
                .setAudience(Audience.tag(tag))//
                //.setNotification(Notification.alert(JSON.toJSONString(responseNoticeMessage)))
                .setMessage(Message.content(JSON.toJSONString(responseNoticeMessage)))  //内部消息不显示，类似web socket
                .build();
    }

    public static PushPayload buildPushObject_all_all_alert() {
        return PushPayload.alertAll(ALERT);
    }

    public static PushPayload buildPushObject_all_alias_alert() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias("alias1"))
                .setNotification(Notification.alert(ALERT))
                .build();
    }

    public static PushPayload buildPushObject_android_tag_alertWithTitle() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag("tag1"))
                .setNotification(Notification.android(ALERT, TITLE, null))
                .build();
    }

    public static PushPayload buildPushObject_android_and_ios() {
        Map<String, String> extras = new HashMap<String, String>();
        extras.put("test", "https://community.jiguang.cn/push");
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .setAlert("alert content")
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle("Android Title")
                                .addExtras(extras).build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .incrBadge(1)
                                .addExtra("extra_key", "extra_value").build())
                        .build())
                .build();
    }

    public static void buildPushObject_with_extra() {

        JsonObject jsonExtra = new JsonObject();
        jsonExtra.addProperty("extra1", 1);
        jsonExtra.addProperty("extra2", false);

        Map<String, String> extras = new HashMap<String, String>();
        extras.put("extra_1", "val1");
        extras.put("extra_2", "val2");

        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.tag("tag1"))
                .setNotification(Notification.newBuilder()
                        .setAlert("alert content")
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle("Android Title")
                                .addExtras(extras)
                                .addExtra("booleanExtra", false)
                                .addExtra("numberExtra", 1)
                                .addExtra("jsonExtra", jsonExtra)
                                .build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .incrBadge(1)
                                .addExtra("extra_key", "extra_value").build())
                        .build())
                .build();

        System.out.println(payload.toJSON());
    }

    public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
        JsonObject sound = new JsonObject();
        sound.add("critical", new JsonPrimitive(1));
        sound.add("name", new JsonPrimitive("default"));
        sound.add("volume", new JsonPrimitive(0.2));
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag_and("tag1", "tag_all"))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(ALERT)
                                .setBadge(5)
                                .setMutableContent(false)
//                                .setSound("happy")
                                .setSound(sound)
                                .addExtra("from", "JPush")
                                .build())
                        .build())
                .setMessage(Message.content(MSG_CONTENT))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .build();
    }

    public static PushPayload buildPushObject_android_newly_support() {

        JsonObject inbox = new JsonObject();
        inbox.add("line1", new JsonPrimitive("line1 string"));
        inbox.add("line2", new JsonPrimitive("line2 string"));
        inbox.add("contentTitle", new JsonPrimitive("title string"));
        inbox.add("summaryText", new JsonPrimitive("+3 more"));

        JsonObject intent = new JsonObject();
        intent.add("url", new JsonPrimitive("intent:#Intent;component=com.jiguang.push/com.example.jpushdemo.SettingActivity;end"));

        Notification notification = Notification.newBuilder()
                .addPlatformNotification(AndroidNotification.newBuilder()
                        .setAlert(ALERT)
                        .setBigPicPath("path to big picture")
                        .setBigText("long text")
                        .setBuilderId(1)
                        .setCategory("CATEGORY_SOCIAL")
                        .setInbox(inbox)
                        .setStyle(1)
                        .setTitle("Alert test")
                        .setPriority(1)
                        .setLargeIcon("http://www.jiguang.cn/largeIcon.jpg")
                        .setIntent(intent)
                        .build())
                .build();
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.all())
                .setNotification(notification)
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .setSendno(ServiceHelper.generateSendno())
                        .build())
                .build();
    }

    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(MSG_CONTENT)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }

    public static PushPayload buildPushObject_all_tag_not() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.tag_not("abc", "123"))
                .setNotification(Notification.alert(ALERT))
                .build();
    }

    public static PushPayload buildPushObject_android_cid() {
        Collection<String> list = new LinkedList<String>();
        list.add("1507bfd3f79558957de");
        list.add("1507bfd3f79554957de");
        list.add("1507bfd3f79555957de");
        list.add("1507bfd3f79556957de");
        list.add("1507ffd3f79545957de");
        list.add("1507ffd3f79457957de");
        list.add("1507ffd3f79456757de");


        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
//                .setAudience(Audience.registrationId("1507bfd3f79558957de"))
                .setAudience(Audience.registrationId(list))
                .setNotification(Notification.alert(ALERT))
                .setCid("cid")
                .build();
    }

}

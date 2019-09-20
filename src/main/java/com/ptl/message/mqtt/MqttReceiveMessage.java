package com.ptl.message.mqtt;

import com.alibaba.fastjson.JSON;
import com.ptl.message.mqtt.config.IotMqttClient;
import com.ptl.message.mqtt.enums.ModuleEnum;
import com.ptl.message.mqtt.handler.MqttReceiveMessageHandler;
import com.ptl.message.mqtt.listener.MessageHandleListener;
import com.ptl.message.mqtt.model.builder.MqttTopicBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * created by panta on 2019/9/4.
 *
 * @author panta
 */
@Component
public class MqttReceiveMessage {

    @Resource
    private MqttReceiveMessageHandler mqttReceiveMessageHandler;

    private Logger logger = LoggerFactory.getLogger(MqttReceiveMessage.class);

//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        String topic = new MqttTopicBuilder().setDeviceId("1").setModuleName(ModuleEnum.HUMIDITY).buildTopic();
//        mqttReceiveMessageHandler.subscribe(topic ,2, (topic1, message1) ->logger.info(""));
//    }
}

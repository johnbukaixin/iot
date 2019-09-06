package com.ptl.message.mqtt;

import com.ptl.message.mqtt.config.IotMqttClient;
import com.ptl.message.mqtt.enums.ModuleEnum;
import com.ptl.message.mqtt.handler.MqttReceiveMessageHandler;
import com.ptl.message.mqtt.listener.MessageHandleListener;
import com.ptl.message.mqtt.model.builder.MqttTopicBuilder;

/**
 * created by panta on 2019/9/4.
 *
 * @author panta
 */
public class MqttReceiveMessage {

    private MqttReceiveMessageHandler handler = new MqttReceiveMessageHandler();

    public static void main(String[] args) {
        MqttReceiveMessage message = new MqttReceiveMessage();
        String topic = new MqttTopicBuilder().setDeviceId("1").setModuleName(ModuleEnum.HUMIDITY).buildTopic();
        message.handler.subscribe(topic ,2, (topic1, message1) -> System.out.println("topic = [" + topic1 + "], message = [" + message1 + "]"));

    }
}

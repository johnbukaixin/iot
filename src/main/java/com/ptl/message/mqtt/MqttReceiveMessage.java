package com.ptl.message.mqtt;

import com.ptl.message.mqtt.config.IotMqttClient;
import com.ptl.message.mqtt.handler.MqttReceiveMessageHandler;

/**
 * created by panta on 2019/9/4.
 *
 * @author panta
 */
public class MqttReceiveMessage {

    private MqttReceiveMessageHandler handler = new MqttReceiveMessageHandler();

    public static void main(String[] args) {
        MqttReceiveMessage message = new MqttReceiveMessage();
        message.handler.subscribe(
                IotMqttClient.getMqttProperties().getTopic(),
                2,
                (topic, message1) -> System.out.println("topic = [" + topic + "], message = [" + message1 + "]"));

    }
}

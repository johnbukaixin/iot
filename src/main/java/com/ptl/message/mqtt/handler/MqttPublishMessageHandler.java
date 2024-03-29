package com.ptl.message.mqtt.handler;

import com.alibaba.fastjson.JSON;
import com.ptl.message.mqtt.config.IotMqttClient;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/**
 * created by panta on 2019/9/5.
 *
 * @author panta
 */
public class MqttPublishMessageHandler implements PublishClientHandler{


    @Override
    public void publish(String topic, MqttMessage message) throws MqttException {

    }

    //发送消息并获取回执
    public void publish(MqttMessage message) throws MqttException {
        MqttTopic mqttTopic = IotMqttClient.getMqttClient().getTopic(IotMqttClient.getMqttProperties().getTopic());
        MqttDeliveryToken token = mqttTopic.publish(message);
        token.waitForCompletion();
        System.out.println("message is published completely! " + token.isComplete());
        System.out.println("messageId:" + token.getMessageId());
        MqttWireMessage response = token.getResponse();
        System.out.println(JSON.toJSONString(response));
    }

    //发送消息并获取回执
    public void publish(MqttTopic mqttTopic, MqttMessage message) throws MqttException {
        MqttDeliveryToken token = mqttTopic.publish(message);
        token.waitForCompletion();
        System.out.println("message is published completely! " + token.isComplete());
        System.out.println("messageId:" + token.getMessageId());
        token.getResponse();
        if (IotMqttClient.getMqttClient().isConnected()) {
            IotMqttClient.getMqttClient().disconnect(10000);
        }
        System.out.println("Disconnected: delivery token \"" + token.hashCode() + "\" received: " + token.isComplete());
    }
}

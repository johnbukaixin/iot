package com.ptl.message.mqtt.handler;

import com.alibaba.fastjson.JSON;
import com.ptl.message.mqtt.config.IotMqttClient;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * created by panta on 2019/9/5.
 *
 * @author panta
 */
@Component
public class MqttPublishMessageHandler implements PublishClientHandler {

    private Logger logger = LoggerFactory.getLogger(MqttPublishMessageHandler.class);

    @Resource(name = "publishClient")
    private MqttClient mqttClient;

    @Value("${mqtt.topic}")
    private String topic;

    @Override
    public void publish(String topic, MqttMessage message) throws MqttException {
        MqttTopic mqttTopic = IotMqttClient.getMqttClient().getTopic(topic);
        MqttDeliveryToken token = mqttTopic.publish(message);
        token.waitForCompletion();
        logger.info("message is published completely!{},messageId:{}", token.isComplete(), token.getMessageId());
        MqttWireMessage response = token.getResponse();

        logger.info("MqttWireMessage response:{}", JSON.toJSONString(response));
    }

    //发送消息并获取回执
    public void publish(MqttMessage message) throws MqttException {
        MqttTopic mqttTopic = IotMqttClient.getMqttClient().getTopic(IotMqttClient.getMqttProperties().getTopic());
        MqttDeliveryToken token = mqttTopic.publish(message);
        token.waitForCompletion();
        logger.info("message is published completely!{},messageId:{}", token.isComplete(), token.getMessageId());
        MqttWireMessage response = token.getResponse();

        logger.info("MqttWireMessage response:{}", JSON.toJSONString(response));
    }

    //发送消息并获取回执
    public void publish(MqttTopic mqttTopic, MqttMessage message) throws MqttException {
        MqttDeliveryToken token = mqttTopic.publish(message);
        token.waitForCompletion();
        logger.info("message is published completely!{},messageId:{}", token.isComplete(), token.getMessageId());
        token.getResponse();

        logger.info("Disconnected: delivery token:{},received:{}" , token.hashCode() , token.isComplete());
    }
}

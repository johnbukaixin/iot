package com.ptl.message.mqtt.listener;

import com.alibaba.fastjson.JSON;
import com.ptl.message.mqtt.config.MqttConnectClient;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * created by panta on 2019/9/9.
 * public void connectionLost(Throwable cause)在断开连接时调用
 * public void messageArrived(MqttTopic topic, MqttMessage message)接收已经预订的发布。
 * public void deliveryComplete(MqttDeliveryToken token))
 * 接收到已经发布的 QoS 1 或 QoS 2 消息的传递令牌时调用。
 * 由 MqttClient.connect 激活此回调。
 *
 * @author panta
 */
@Component
public class IotPublishCallback implements MqttCallback {
    private Logger logger = LoggerFactory.getLogger(IotPublishCallback.class);

    private MqttConnectClient mqttConnectClient;


    public IotPublishCallback(MqttConnectClient mqttConnectClient) {
        this.mqttConnectClient = mqttConnectClient;
    }

    @Resource
    private MqttConnectOptions mqttConnectOptions;

    @Override
    public void connectionLost(Throwable cause) {
        reconnectBroker(cause);

    }

    private void reconnectBroker(Throwable cause) {

        if (null == mqttConnectClient) {
            return;
        }
        logger.info("The client has lost its connection to the broker,client={},e=", mqttConnectClient, cause);
        if (!mqttConnectClient.mqttClient().isConnected()) {
            try {
                mqttConnectClient.mqttClient().connect(mqttConnectOptions);
                logger.info("Client reconnects successfully");
            } catch (MqttException e) {
                logger.error("Client reconnects failed", e);
                e.printStackTrace();
            }
        }


    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        logger.info("topic1：{}，message1：{}", topic, JSON.toJSONString(mqttConnectClient));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
//        logger.info("topic1：{}，message1：{}", JSON.toJSONString(token));

        try {
            logger.info("已经发布的 QoS 1 或 QoS 2 消息的传递令牌时调用,token={}", token.getMessage());

        }catch (Exception e){
            logger.error("eeeeeee",e);
        }


    }
}

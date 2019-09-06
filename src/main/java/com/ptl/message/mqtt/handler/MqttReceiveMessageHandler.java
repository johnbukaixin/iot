package com.ptl.message.mqtt.handler;

import com.ptl.message.mqtt.config.IotMqttClient;
import com.ptl.message.mqtt.listener.MessageHandleListener;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * created by panta on 2019/9/5.
 *
 * @author panta
 */
@Component
public class MqttReceiveMessageHandler implements SubscribeClientHandler,IMqttMessageListener {
    private static Map<String, MessageHandleListener> subscribeInfo = new HashMap<>();
    public static Map<String, MessageHandleListener> getSubscribeInfo() {
        return subscribeInfo;
    }

    @Resource(name = "subscribeClient")
    private MqttClient mqttClient;
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        //获取监听器
        MessageHandleListener listener = getSubscribeInfo().get(topic);
        if (null != listener) {
            listener.handleMessage(topic, message.toString());
        }
    }

    @Override
    public void unSubscribe(String topic) throws MqttException {
        subscribeInfo.remove(topic);
        mqttClient.unsubscribe(topic);
    }

    @Override
    public void subscribe(String topic, int qos, MessageHandleListener messageHandleListener) {
        subscribeInfo.put(topic,messageHandleListener);
        try {
            IotMqttClient.getMqttClient().subscribe(topic,qos,this);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void subscribe(String topic, MessageHandleListener messageHandleListener) {
        subscribeInfo.put(topic,messageHandleListener);
        try {
            mqttClient.subscribe(topic,this);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}

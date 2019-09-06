package com.ptl.message.mqtt.handler;

import com.ptl.message.mqtt.listener.MessageHandleListener;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * created by panta on 2019/9/5.
 *
 * @author panta
 */
public interface SubscribeClientHandler{

    void unSubscribe(String topic) throws MqttException;
    void subscribe(String topic, int qos, MessageHandleListener messageHandleListener);
    void subscribe(String topic, MessageHandleListener messageHandleListener);

}

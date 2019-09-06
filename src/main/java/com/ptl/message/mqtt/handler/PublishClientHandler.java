package com.ptl.message.mqtt.handler;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * created by panta on 2019/9/5.
 *
 * @author panta
 */
public interface PublishClientHandler {

    void publish(String topic, MqttMessage message) throws MqttException;

}

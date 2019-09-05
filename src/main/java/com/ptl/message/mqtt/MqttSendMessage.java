package com.ptl.message.mqtt;

import com.ptl.message.mqtt.handler.MqttPublishMessageHandler;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * created by panta on 2019/9/4.
 *
 * @author panta
 */
public class MqttSendMessage {

    public static void main(String[] args) throws MqttException {

        MqttPublishMessageHandler handler = new MqttPublishMessageHandler();
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setQos(2);
        mqttMessage.setPayload("hello world".getBytes());
        for (int i = 0 ;i <  100 ; i++){
            handler.publish(mqttMessage);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

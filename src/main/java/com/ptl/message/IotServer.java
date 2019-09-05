package com.ptl.message;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.Closeable;

/**
 * created by panta on 2019/9/5.
 *
 * @author panta
 */
public interface IotServer extends Closeable {

    void connect();

    @Override
    void close();

}

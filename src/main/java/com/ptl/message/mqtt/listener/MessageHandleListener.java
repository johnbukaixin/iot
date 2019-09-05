package com.ptl.message.mqtt.listener;

/**
 * created by panta on 2019/9/5.
 *
 * @author panta
 */
@FunctionalInterface
public interface MessageHandleListener {
    void handleMessage(String topic, String message);

}

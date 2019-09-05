package com.ptl.message.mqtt.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created by panta on 2019/9/5.
 *
 * @author panta
 */
@Configuration
public class MqttConnectConfig {

    @Value("{mqtt.publish.clientId}")
    private String publishClientId;

    @Value("{mqtt.subscribe.clientId}")
    private String subscribeClientId;

    @Value("{mqtt.serverUri}")
    private String serverUri;

    @Value("{mqtt.topic}")
    private String topic;
    @Bean
    public void buildPublishClient(){
        try {
            MqttClient client = new MqttClient(serverUri,publishClientId,new MemoryPersistence());
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
}

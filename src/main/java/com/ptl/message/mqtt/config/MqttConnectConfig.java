package com.ptl.message.mqtt.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private Logger logger = LoggerFactory.getLogger(MqttConnectConfig.class);

    @Value("${mqtt.publish.clientId}")
    private String publishClientId;

    @Value("${mqtt.subscribe.clientId}")
    private String subscribeClientId;


    @Value("${mqtt.serverUri}")
    private String serverUri;

    @Value("${mqtt.topic}")
    private String topic;

    @Value("${mqtt.username}")
    private String userName;

    @Value("${mqtt.password}")
    private String password;

    @Value("${mqtt.keep.alive.interval}")
    private int keepAliveInterval;

    @Value("${mqtt.qos}")
    private int qos;

    @Autowired
    private MqttConnectOptions options;


    @Bean
    public MqttConnectOptions mqttConnectOptions(){
        MqttConnectOptions options = new MqttConnectOptions();

        options.setKeepAliveInterval(keepAliveInterval);
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(password.toCharArray());
        return options;
    }
    @Bean
    public MqttClient buildPublishClient(){
        MqttClient client = null;
        try {
            client = new MqttClient(serverUri,publishClientId,new MemoryPersistence());
            client.connect(options);
            logger.info("publish client is connected,serverUri:{},clientId:{}",serverUri,publishClientId);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Bean
    public MqttClient buildSubscribeClient(){
        MqttClient client = null;
        try {
            client = new MqttClient(serverUri,subscribeClientId,new MemoryPersistence());
            client.connect(options);
            logger.info("subscribe client is connected,serverUri:{},clientId:{}",serverUri,subscribeClientId);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return client;
    }
 }

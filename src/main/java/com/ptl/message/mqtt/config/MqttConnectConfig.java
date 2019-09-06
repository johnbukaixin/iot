package com.ptl.message.mqtt.config;

import org.apache.logging.log4j.util.Strings;
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
import org.springframework.util.Assert;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

/**
 * created by panta on 2019/9/5.
 *
 * @author panta
 */
@Configuration
public class MqttConnectConfig {
    private Logger logger = LoggerFactory.getLogger(MqttConnectConfig.class);

    private String publishClientId;

    @Value("${mqtt.publish.clientId}")
    public void setPublishClientId(String publishClientId) throws UnknownHostException {
        this.publishClientId = buildClientId(publishClientId);
    }

    private String subscribeClientId;

    @Value("${mqtt.subscribe.clientId}")
    public void setSubscribeClientId(String subscribeClientId) throws UnknownHostException {
        this.subscribeClientId = buildClientId(subscribeClientId);
    }

    private String serverUri;

    @Value("${mqtt.serverUri}")
    public void setServerUri(String serverUri) {
        Assert.notNull(serverUri, "连接地址为必须参数");
        this.serverUri = serverUri;
    }

    @Value("${mqtt.topic}")
    private String topic;

    @Value("${mqtt.username}")
    private String userName;

    @Value("${mqtt.password}")
    private String password;

    @Value("${mqtt.keep.alive.interval:60}")
    private int keepAliveInterval;

    @Value("${mqtt.qos:0}")
    private int qos;

    @Value("${mqtt.connection.timeout:30}")
    private int connectTimeout;

    @Autowired
    private MqttConnectOptions options;


    private String buildClientId(String clientId) throws UnknownHostException {
       return Strings.isEmpty(clientId) ? Inet4Address.getLocalHost().getHostAddress() + UUID.randomUUID().toString().replace("-","") : clientId;
    }
    @Bean
    public MqttConnectOptions mqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();

        options.setKeepAliveInterval(keepAliveInterval);
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(password.toCharArray());
        options.setConnectionTimeout(connectTimeout);
        return options;
    }

    @Bean
    public MqttClient publishClient() {
        MqttClient client = null;
        try {
            client = new MqttClient(serverUri, publishClientId, new MemoryPersistence());
            client.connect(options);
            logger.info("publish client is connected,serverUri:{},clientId:{}", serverUri, publishClientId);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Bean
    public MqttClient subscribeClient() {
        MqttClient client = null;
        try {
            client = new MqttClient(serverUri, subscribeClientId, new MemoryPersistence());
            client.connect(options);
            logger.info("subscribe client is connected,serverUri:{},clientId:{}", serverUri, subscribeClientId);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return client;
    }
}

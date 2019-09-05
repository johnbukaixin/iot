package com.ptl.message.mqtt.config;

import com.alibaba.fastjson.JSON;
import com.ptl.message.IotServer;
import org.apache.commons.io.FileUtils;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystemNotFoundException;
import java.util.UUID;

/**
 * created by panta on 2019/9/4.
 *
 * @author panta
 */
public class IotMqttClient implements IotServer {

    private static MqttClient mqttClient;

    private static String clientId = UUID.randomUUID().toString();


    public static MqttClient getMqttClient() {
        if (mqttClient == null) {
            for (String serverUri : mqttProperties.getServerURIs()) {
                try {
                    mqttClient = new MqttClient(serverUri, clientId, new MemoryPersistence());
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!mqttClient.isConnected()) {
            connect(mqttProperties);
        }


        return mqttClient;
    }


    public static class MqttProperties extends MqttConnectOptions {
        private String topic;


        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }


    }

    private static MqttProperties mqttProperties = readMqttProperties();

    private static MqttProperties readMqttProperties() {
        URL url = MqttProperties.class.getClassLoader().getResource("config.json");
        if (url == null) {
            throw new FileSystemNotFoundException("config.json不存在，请保证config.json存在");
        }

        String filename = url.getPath();

        File file = new File(filename);
        MqttProperties jsonObject = null;
        try {
            String content = FileUtils.readFileToString(file, "UTF-8");
            jsonObject = JSON.parseObject(content, MqttProperties.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null == jsonObject) {
            throw new IllegalArgumentException("参数配置找不到");
        }
        return jsonObject;

    }

    public static MqttProperties getMqttProperties() {
        return mqttProperties;
    }


    private static void connect(MqttConnectOptions options) {

        try {
            mqttClient.connect(options);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void connect() {
        connect(mqttProperties);
    }

    @Override
    public void close() {
        try {
            mqttClient.close();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


}

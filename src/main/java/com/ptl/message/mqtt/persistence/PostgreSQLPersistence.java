package com.ptl.message.mqtt.persistence;

import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.springframework.stereotype.Component;

import java.util.Enumeration;

/**
 * created by panta on 2019/9/6.
 *
 * @author panta
 */
@Component
public class PostgreSQLPersistence implements MqttClientPersistence {
    @Override
    public void open(String clientId, String serverURI) throws MqttPersistenceException {

    }

    @Override
    public void close() throws MqttPersistenceException {

    }

    @Override
    public void put(String key, MqttPersistable persistable) throws MqttPersistenceException {

    }

    @Override
    public MqttPersistable get(String key) throws MqttPersistenceException {
        return null;
    }

    @Override
    public void remove(String key) throws MqttPersistenceException {

    }

    @Override
    public Enumeration keys() throws MqttPersistenceException {
        return null;
    }

    @Override
    public void clear() throws MqttPersistenceException {

    }

    @Override
    public boolean containsKey(String key) throws MqttPersistenceException {
        return false;
    }
}

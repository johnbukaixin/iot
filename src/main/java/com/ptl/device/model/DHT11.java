package com.ptl.device.model;

import com.pi4j.component.sensor.impl.GpioSensorComponent;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinState;

import java.util.HashMap;
import java.util.Map;

/**
 * created by panta on 2019/9/3.
 *
 * @author panta
 */
public class DHT11 extends GpioSensorComponent {
    private Map<String, String> properties = new HashMap<String, String>();

    /**
     * 温度
     */
    private double temperature;
    /**
     * 湿度
     */
    private double humidity;

    public DHT11(GpioPinDigitalInput pin, PinState openState, PinState closedState) {
        super(pin, openState, closedState);
    }

    public DHT11(GpioPinDigitalInput pin) {
        super(pin);
    }


    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }


    @Override
    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public void setProperty(String key, String value) {
        this.properties.put(key,value);
    }

    @Override
    public String getProperty(String key) {
        return this.properties.get(key);
    }
}

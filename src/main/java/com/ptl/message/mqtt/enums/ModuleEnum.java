package com.ptl.message.mqtt.enums;

/**
 * created by panta on 2019/9/6.
 *
 * @author panta
 */
public enum ModuleEnum {

    TEMPERATURE(1,"温度模块"),
    HUMIDITY(2,"湿度");

    private String value;

    private int code;

    ModuleEnum(int code, String value) {
        this.value = value;
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public int getCode() {
        return code;
    }
}

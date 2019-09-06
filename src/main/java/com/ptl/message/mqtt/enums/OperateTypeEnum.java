package com.ptl.message.mqtt.enums;

/**
 * created by panta on 2019/9/6.
 *
 * @author panta
 */
public enum OperateTypeEnum {

    CLOSE(0,"设备关闭"),
    OPEN(1,"设备开启")
    ;
    private int code;

    private String value;

    OperateTypeEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}

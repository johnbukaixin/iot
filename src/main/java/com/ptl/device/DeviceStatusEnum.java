package com.ptl.device;

import com.pi4j.io.gpio.PinState;

/**
 * created by panta on 2019/9/3.
 * 设备状态枚举
 * @author panta
 */
public enum DeviceStatusEnum {

    CLOSED(),
    OPEN(),
    BROKEN();
}

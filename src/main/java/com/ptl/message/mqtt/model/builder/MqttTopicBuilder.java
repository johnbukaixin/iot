package com.ptl.message.mqtt.model.builder;

import com.ptl.message.mqtt.enums.ModuleEnum;
import org.apache.logging.log4j.util.Strings;

/**
 * created by panta on 2019/9/6.
 * topic like "appName/moduleName/deviceId"
 *
 * if deviceId value is null,This means that all devices  within this module named moduleName will receive instructions
 * @author panta
 */
public class MqttTopicBuilder {

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 模块名称
     */
    private ModuleEnum moduleName;

    /**
     * 设备id
     */
    private String deviceId;

    public MqttTopicBuilder setAppName(String appName) {
        this.appName = appName;
        return this;
    }

    public MqttTopicBuilder setModuleName(ModuleEnum moduleName) {
        this.moduleName = moduleName;
        return this;
    }

    public MqttTopicBuilder setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }



    public String buildTopic() {
        StringBuilder stringBuilder = new StringBuilder();
        String[] appPath = System.getProperty("user.dir").replaceAll("\\\\", "/").split("/");
        stringBuilder.append(this.appName == null ? appPath[appPath.length - 1] : this.appName);

        if (this.moduleName == null) {
            return stringBuilder.toString();
        }
        stringBuilder.append("/");
        stringBuilder.append(this.moduleName);

        if (Strings.isEmpty(this.deviceId)) {
            return stringBuilder.toString();

        }
        stringBuilder.append("/");
        stringBuilder.append(this.deviceId);


        return stringBuilder.toString();
    }

}

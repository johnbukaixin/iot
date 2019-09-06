package com.ptl.message.mqtt.model.builder;

import com.ptl.message.mqtt.enums.ModuleEnum;
import com.ptl.message.mqtt.enums.OperateTypeEnum;

import java.io.File;

/**
 * created by panta on 2019/9/6.
 * topic like "appName/moduleName/deviceId/operate"
 * @author panta
 */
public class MqttTopicBuilder{

    private String appName;

    private ModuleEnum moduleName;

    private String deviceId;

    private OperateTypeEnum operate;

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

    public MqttTopicBuilder setOperate(OperateTypeEnum operate) {
        this.operate = operate;
        return this;
    }


    public String buildTopic(MqttTopicBuilder builder){
        StringBuilder stringBuilder = new StringBuilder();
        String[] appPath = System.getProperty("user.dir").replaceAll("\\\\", "/").split("/");
        stringBuilder.append(builder.appName == null ? appPath[appPath.length-1] : builder.appName);

        if (builder.moduleName != null){
            stringBuilder.append("/");
            stringBuilder.append(builder.moduleName);
        }
        if (builder.deviceId != null){
            stringBuilder.append("/");
            stringBuilder.append(builder.deviceId);
        }

        if (builder.operate != null){
            stringBuilder.append("/");
            stringBuilder.append(builder.operate);
        }

        return stringBuilder.toString();
    }

}

package com.ptl.message.mqtt;

import com.alibaba.fastjson.JSON;
import com.ptl.device.DeviceTypeEnum;
import com.ptl.message.mqtt.enums.ModuleEnum;
import com.ptl.message.mqtt.enums.OperateTypeEnum;
import com.ptl.message.mqtt.handler.MqttPublishMessageHandler;
import com.ptl.message.mqtt.model.builder.MqttTopicBuilder;
import com.ptl.message.mqtt.model.builder.Payload;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * created by panta on 2019/9/4.
 *
 * @author panta
 */

@Component
public class MqttSendMessage {

    @Resource
    private MqttPublishMessageHandler mqttPublishMessageHandler;
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        MqttMessage mqttMessage = new MqttMessage();
//        mqttMessage.setQos(2);
//
//        Payload payload = new Payload();
//        String topic = new MqttTopicBuilder().setDeviceId("1").setModuleName(ModuleEnum.HUMIDITY).buildTopic();
//        payload.setTopic(topic);
//        payload.setData("hello world");
//        payload.setDeviceType(DeviceTypeEnum.GPIO.name());
//        payload.setOperateType(OperateTypeEnum.OPEN.getValue());
//        mqttMessage.setPayload(JSON.toJSONString(payload).getBytes());
//
//        for (int i = 0 ;i <  100 ; i++){
//            mqttPublishMessageHandler.publish(topic,mqttMessage);
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}

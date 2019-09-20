package com.ptl.push;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * created by panta on 2019/9/18.
 *
 * @author panta
 */
@Component
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "jpush")
public class JpushConfig {

    /**
     * 极光申请的app key
     */
    private String appKey;
    /**
     * 极光申请的secret
     */
    private String masterSecret;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getMasterSecret() {
        return masterSecret;
    }

    public void setMasterSecret(String masterSecret) {
        this.masterSecret = masterSecret;
    }
}

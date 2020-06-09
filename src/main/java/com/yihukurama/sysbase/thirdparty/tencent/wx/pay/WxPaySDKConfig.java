package com.yihukurama.sysbase.thirdparty.tencent.wx.pay;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;

@Configuration
@ConfigurationProperties(prefix ="wxpayconfig")
@Data
public class WxPaySDKConfig {

    private String appId;
    private String appSecret;
    private String miniAppId;
    private String appAppId;
    private String mchId;
    private String mchKey;
    private String keyPath;
    private SSLContext sslContext;
    private String notifyUrl;
    private String p2pwd;

}

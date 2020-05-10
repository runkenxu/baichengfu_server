package com.yihukurama.sysbase.thirdparty.ali;

import com.lly835.bestpay.config.AliPayConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix ="alipay")
@Data
public class MyAlipayConfig {
    private String appId;
    private String aliPayPublicKey;
    private String privateKey;
    private String returnUrl;
    private String notifyUrl;
}
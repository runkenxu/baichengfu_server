package com.yihukurama.sysbase.module.archives.service.domainservice;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import com.mysql.cj.util.LogUtils;
import com.yihukurama.sysbase.model.PushNotifyEntity;
import com.yihukurama.sysbase.module.app.designp.observer.AppEventPublisher;
import com.yihukurama.sysbase.module.app.designp.observer.event.PushEvent;
import com.yihukurama.sysbase.module.archives.domain.PushNotify;
import com.yihukurama.tkmybatisplus.app.exception.TipsException;
import com.yihukurama.tkmybatisplus.app.utils.LogUtil;
import com.yihukurama.tkmybatisplus.framework.service.domainservice.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static cn.jpush.api.push.model.notification.PlatformNotification.ALERT;
import static org.bouncycastle.jcajce.spec.TLSKeyMaterialSpec.MASTER_SECRET;

@Service
public class PushNotifyService extends CrudService<PushNotifyEntity> {

    @Autowired
    AppEventPublisher appEventPublisher;
    
    @Override
    public PushNotifyEntity create(PushNotifyEntity pushNotifyEntity) throws TipsException {
        if(pushNotifyEntity.getSenderType() == 10){
            JPushClient jpushClient = new JPushClient(MASTER_SECRET, "352895bcabd0bc001a6dbf99", null, ClientConfig.getInstance());

            // For push, all you need do is to build PushPayload object.
            PushPayload payload = PushPayload.alertAll(ALERT);

            try {
                PushResult result = jpushClient.sendPush(payload);
                LogUtil.infoLog(this, "Got result - " + result);

            } catch (APIConnectionException e) {
                // Connection error, should retry later
                LogUtil.errorLog(this,"Connection error, should retry later"+e);

            } catch (APIRequestException e) {
                // Should review the error, and fix the request
                LogUtil.errorLog(this,"Should review the error, and fix the request"+e);
                LogUtil.infoLog(this,"HTTP Status: " + e.getStatus());
                LogUtil.infoLog(this,"Error Code: " + e.getErrorCode());
                LogUtil.infoLog(this,"Error Message: " + e.getErrorMessage());
            }
        }
        appEventPublisher.publishEvent(new PushEvent(pushNotifyEntity));
        return super.create(pushNotifyEntity);
    }
}
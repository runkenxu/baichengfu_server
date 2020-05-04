package com.yihukurama.sysbase.module.pay.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.common.models.AlipayTradeCreateResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeRefundResponse;
import com.yihukurama.sysbase.model.OrderEntity;
import com.yihukurama.sysbase.module.archives.domain.Order;
import com.yihukurama.sysbase.module.archives.service.domainservice.OrderService;
import com.yihukurama.sysbase.module.pay.IPay;
import com.yihukurama.tkmybatisplus.app.exception.TipsException;
import com.yihukurama.tkmybatisplus.app.utils.EmptyUtil;
import com.yihukurama.tkmybatisplus.app.utils.LogUtil;
import com.yihukurama.tkmybatisplus.framework.web.dto.Request;
import com.yihukurama.tkmybatisplus.framework.web.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("AliPayService")
public class AliPayService implements IPay {

    @Autowired
    OrderService orderService;

    @Override
    public Result unifiedOrder(Request<Order> request) throws TipsException {
        Order order = request.getData();
        OrderEntity orderEntity = orderService.load(order);
        try {
            // 2. 发起API调用（以支付能力下的统一收单交易创建接口为例）
            AlipayTradeCreateResponse response = Factory.Payment.Common().create("App",
                    order.getNum(), orderEntity.getPaidPrice().toString(), order.getAppuserId());
            // 3. 处理响应或异常
            if ("10000".equals(response.code)) {
                return Result.successed(response,"阿里下单成功");
            } else {
                String errMsg = "阿里下单失败，原因：" + response.msg + "，" + response.subMsg;
                LogUtil.errorLog(this,errMsg);
                return Result.failed("阿里下单失败，原因：" + response.msg + "，" + response.subMsg);
            }
        } catch (Exception e) {
            String errMsg = "阿里下单遭遇异常，原因：" + e.getMessage();
            LogUtil.errorLog(this,errMsg);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public Result refund(Request<Order> request) throws TipsException {
        Order order = request.getData();
        OrderEntity orderEntity = orderService.load(order);
        try {
            // 2. 发起API调用（以支付能力下的统一收单交易创建接口为例）
            AlipayTradeRefundResponse response = Factory.Payment.Common().refund(
                    order.getNum(), orderEntity.getPaidPrice().toString());
            // 3. 处理响应或异常
            if ("10000".equals(response.code)) {
                return Result.successed(response,"退款成功");
            } else {
                String errMsg = "退款失败，原因：" + response.msg + "，" + response.subMsg;
                LogUtil.errorLog(this,errMsg);
                return Result.failed("退款失败，原因：" + response.msg + "，" + response.subMsg);
            }
        } catch (Exception e) {
            String errMsg = "退款遭遇异常，原因：" + e.getMessage();
            LogUtil.errorLog(this,errMsg);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public String payNotfiy(String requestBody) throws TipsException {
        JSONObject payResponse = JSON.parseObject(requestBody);
        if(payResponse!=null){
            String resultStatus = payResponse.getString("resultStatus");
            if(("9000").equals(resultStatus)){
                JSONObject result = payResponse.getJSONObject("result");
                if(result!=null){
                    //订单号
                    String out_trade_no = result.getString("out_trade_no");
                    //支付宝流水号
                    String trade_no = result.getString("trade_no");
                    //金额
                    String total_amount = result.getString("total_amount");
                    if(EmptyUtil.isEmpty(out_trade_no)
                    || EmptyUtil.isEmpty(trade_no)
                    || EmptyUtil.isEmpty(total_amount)){
                        LogUtil.errorLog(this,"支付宝回调出现问题,result错误，回调参数为:"+JSON.toJSONString(requestBody));

                    }
                    OrderEntity orderEntity = new OrderEntity();
                    orderEntity.setNum(out_trade_no);
                    orderEntity = orderService.loadOneByCondition(orderEntity);
                    if(orderEntity == null){
                        LogUtil.errorLog(this,"支付宝回调出现问题,找不到订单,回调参数为:"+JSON.toJSONString(requestBody));
                    }
                    BigDecimal total = new BigDecimal(total_amount);
                    if(!orderEntity.getPaidPrice().equals(total)){
                        LogUtil.errorLog(this,"支付宝回调出现问题,支付金额与订单金额不一致,回调参数为:"+JSON.toJSONString(requestBody));
                    }
                    orderEntity.setTradeNo(trade_no);
                    orderService.update(orderEntity);
                }
            }else{
                LogUtil.errorLog(this,"支付宝回调出现问题,参数为:"+JSON.toJSONString(requestBody));
            }
        }
        return null;
    }
}

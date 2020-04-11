package com.yihukurama.sysbase.module.app.impl;

import com.yihukurama.sysbase.controller.app.dto.FocusDesignerDto;
import com.yihukurama.sysbase.controller.app.dto.StoreSampleRoomDto;
import com.yihukurama.sysbase.mapper.AppuserMapper;
import com.yihukurama.sysbase.mapper.DesignerMapper;
import com.yihukurama.sysbase.model.*;
import com.yihukurama.sysbase.module.app.IPerson;
import com.yihukurama.sysbase.module.archives.domain.SampleRoom;
import com.yihukurama.sysbase.module.archives.service.domainservice.*;
import com.yihukurama.tkmybatisplus.app.exception.TipsException;
import com.yihukurama.tkmybatisplus.app.utils.EmptyUtil;
import com.yihukurama.tkmybatisplus.framework.web.dto.Request;
import com.yihukurama.tkmybatisplus.framework.web.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明： app用户个人的接口
 * @author yihukurama
 * @date Created in 17:06 2020/4/4
 *       Modified by yihukurama in 17:06 2020/4/4
 */
@Service
public class PersonService implements IPerson {

    @Autowired
    AppuserDesignerService appuserDesignerService;
    @Autowired
    AppuserService appuserService;
    @Autowired
    AppuserMapper appuserMapper;
    @Autowired
    DesignerService designerService;
    @Autowired
    DesignerMapper designerMapper;
    @Autowired
    AppuserSampleService appuserSampleService;
    @Autowired
    SampleRoomService sampleRoomService;

    @Override
    public Result storeSampleRoom(Request<StoreSampleRoomDto> request) throws TipsException {
        AppuserSampleEntity appuserSampleEntity = new AppuserSampleEntity();
        appuserSampleEntity.setAppuserId(request.getData().getAppuserId());
        appuserSampleEntity.setSampleId(request.getData().getSampleId());

        List<AppuserSampleEntity> appuserSampleEntityList = appuserSampleService.list(appuserSampleEntity);
        if(!EmptyUtil.isEmpty(appuserSampleEntityList)){
            return Result.failed(null,"您已收藏",-1);
        }
        appuserSampleEntity = appuserSampleService.create(appuserSampleEntity);
        if(appuserSampleEntity == null){
            return Result.failed(null,"收藏失败",-20);

        }
        //增加收藏数和权重数
        SampleRoomEntity sampleRoomEntity = new SampleRoomEntity();
        sampleRoomEntity.setId(request.getData().getSampleId());
        sampleRoomEntity = sampleRoomService.load(sampleRoomEntity);
        sampleRoomEntity.setFocusCount(sampleRoomEntity.getFocusCount()+1);
        sampleRoomEntity.setOrderCount(sampleRoomEntity.getOrderCount()+1);
        sampleRoomService.update(sampleRoomEntity);
        return Result.successed(appuserSampleEntity,"收藏成功");
    }

    @Override
    public Result unStoreSampleRoom(Request<StoreSampleRoomDto> request) throws TipsException {
        AppuserSampleEntity appuserSampleEntity = new AppuserSampleEntity();
        appuserSampleEntity.setAppuserId(request.getData().getAppuserId());
        appuserSampleEntity.setSampleId(request.getData().getSampleId());

        List<AppuserSampleEntity> appuserSampleEntityList = appuserSampleService.list(appuserSampleEntity);
        if(EmptyUtil.isEmpty(appuserSampleEntityList)){
            return Result.failed(null,"您已取消收藏",-1);
        }
        int removeCount = appuserSampleService.remove(appuserSampleEntityList.get(0));
        if(removeCount == 1){
            //减少收藏数和权重数
            SampleRoomEntity sampleRoomEntity = new SampleRoomEntity();
            sampleRoomEntity.setId(request.getData().getSampleId());
            sampleRoomEntity = sampleRoomService.load(sampleRoomEntity);
            if(sampleRoomEntity.getFocusCount() !=  null || sampleRoomEntity.getFocusCount() > 0){
                sampleRoomEntity.setFocusCount(sampleRoomEntity.getFocusCount()-1);
            }else{
                sampleRoomEntity.setFocusCount(0);
            }
            if(sampleRoomEntity.getFocusCount() !=  null || sampleRoomEntity.getFocusCount() > 0){
                sampleRoomEntity.setOrderCount(sampleRoomEntity.getOrderCount()-1);
            }else{
                sampleRoomEntity.setOrderCount(0);
            }
            sampleRoomService.update(sampleRoomEntity);
            return Result.successed(appuserSampleEntity,"取消收藏成功");
        }
        return Result.failed(null,"取消收藏失败",-20);
    }

    @Override
    public Result focusDesigner(Request<FocusDesignerDto> request) throws TipsException {
        FocusDesignerDto focusDesignerDto = request.getData();
        AppuserDesignerEntity appuserDesignerEntity = new AppuserDesignerEntity();
        appuserDesignerEntity.setAppuserId(focusDesignerDto.getAppuserId());
        appuserDesignerEntity.setDesignerId(focusDesignerDto.getDesignerId());


        List<AppuserDesignerEntity> appuserDesignerEntities =  appuserDesignerService.list(appuserDesignerEntity);
        if(!EmptyUtil.isEmpty(appuserDesignerEntities)){
            return Result.failed(appuserDesignerEntities.get(0),"用户已关注",-1);
        }
        //获取设计师身份数据
        String designerId = focusDesignerDto.getDesignerId();
        DesignerEntity designerEntity = designerMapper.selectByPrimaryKey(designerId);

        appuserDesignerEntity.setGloryValue(designerEntity.getGloryValue());
        appuserDesignerEntity.setHeadUrl(designerEntity.getHeadUrl());
        appuserDesignerEntity.setNickName(designerEntity.getNickName());
        appuserDesignerEntity.setStyle(designerEntity.getStyle());

        appuserDesignerEntity = appuserDesignerService.create(appuserDesignerEntity);

        if(appuserDesignerEntity == null){
            return Result.failed(null,"关注失败",-1);
        }

        return Result.successed(appuserDesignerEntity,"新增关注成功");

    }

    @Override
    public Result unFocusDesigner(Request<FocusDesignerDto> request) throws TipsException {
        FocusDesignerDto focusDesignerDto = request.getData();
        AppuserDesignerEntity appuserDesignerEntity = new AppuserDesignerEntity();
        appuserDesignerEntity.setAppuserId(focusDesignerDto.getAppuserId());
        appuserDesignerEntity.setDesignerId(focusDesignerDto.getDesignerId());


        List<AppuserDesignerEntity> appuserDesignerEntities =  appuserDesignerService.list(appuserDesignerEntity);
        if(EmptyUtil.isEmpty(appuserDesignerEntities)){
            return Result.failed(appuserDesignerEntities.get(0),"已取消关注",-1);
        }

        appuserDesignerService.remove(appuserDesignerEntities.get(0));

        return Result.successed(appuserDesignerEntity,"取消关注成功");

    }

    @Override
    public Result readSampleRoom(Request<StoreSampleRoomDto> request) throws TipsException {
        //增加浏览数和权重数
        SampleRoomEntity sampleRoomEntity = new SampleRoomEntity();
        sampleRoomEntity.setId(request.getData().getSampleId());
        sampleRoomEntity = sampleRoomService.load(sampleRoomEntity);
        sampleRoomEntity.setSFavoriteNumber(sampleRoomEntity.getSFavoriteNumber()+1);
        sampleRoomEntity.setOrderCount(sampleRoomEntity.getOrderCount()+1);
        sampleRoomService.update(sampleRoomEntity);
        return Result.successed("添加浏览数成功");
    }
}

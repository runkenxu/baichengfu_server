package com.yihukurama.sysbase.module.app;

import com.yihukurama.sysbase.controller.app.dto.ClickGoodDto;
import com.yihukurama.sysbase.controller.app.dto.FocusDesignerDto;
import com.yihukurama.sysbase.controller.app.dto.StoreSampleRoomDto;
import com.yihukurama.sysbase.controller.app.dto.StoreTopicDto;
import com.yihukurama.tkmybatisplus.app.exception.TipsException;
import com.yihukurama.tkmybatisplus.framework.web.dto.Request;
import com.yihukurama.tkmybatisplus.framework.web.dto.Result;

public interface IPerson {

    /**
     * 说明： 收藏样板间
     * @author yihukurama
     * @date Created in 14:15 2020/4/11
     *       Modified by yihukurama in 14:15 2020/4/11
     */
    Result storeSampleRoom(Request<StoreSampleRoomDto> request) throws TipsException;



    /**
     * 说明： 取消收藏样板间
     * @author yihukurama
     * @date Created in 14:15 2020/4/11
     *       Modified by yihukurama in 14:15 2020/4/11
     */
    Result unStoreSampleRoom(Request<StoreSampleRoomDto> request) throws TipsException;
    /**
     * 说明： 关注设计师
     * @author yihukurama
     * @date Created in 17:05 2020/4/4
     *       Modified by yihukurama in 17:05 2020/4/4
     */
    Result focusDesigner(Request<FocusDesignerDto> request) throws TipsException;
    /**
     * 说明： 取关设计师
     * @author yihukurama
     * @date Created in 17:05 2020/4/4
     *       Modified by yihukurama in 17:05 2020/4/4
     */
    Result unFocusDesigner(Request<FocusDesignerDto> request) throws TipsException;

    /**
     * 说明： 浏览样板间
     * @author yihukurama
     * @date Created in 16:20 2020/4/11
     *       Modified by yihukurama in 16:20 2020/4/11
     */
    Result readSampleRoom(Request<StoreSampleRoomDto> request) throws TipsException;

    /**
     * 说明： 浏览话题
     * @author yihukurama
     * @date Created in 16:37 2020/4/19
     *       Modified by yihukurama in 16:37 2020/4/19
     */
    Result readTopic(Request<StoreTopicDto> request) throws TipsException;

    /**
     * 说明： 收藏话题
     * @author yihukurama
     * @date Created in 16:38 2020/4/19
     *       Modified by yihukurama in 16:38 2020/4/19
     */
    Result storeTopic(Request<StoreTopicDto> request) throws TipsException;

    /**
     * 说明： 取消收藏话题
     * @author yihukurama
     * @date Created in 16:39 2020/4/19
     *       Modified by yihukurama in 16:39 2020/4/19
     */
    Result unStoreTopic(Request<StoreTopicDto> request) throws TipsException;

    /**
     * 说明： 评论点赞
     * @author yihukurama
     * @date Created in 17:29 2020/4/19
     *       Modified by yihukurama in 17:29 2020/4/19
     */
    Result clickGood(Request<ClickGoodDto> request) throws TipsException;
    /**
     * 说明： 取消评论点赞
     * @author yihukurama
     * @date Created in 17:30 2020/4/19
     *       Modified by yihukurama in 17:30 2020/4/19
     */
    Result unClickGood(Request<ClickGoodDto> request) throws TipsException;
}

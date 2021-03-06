package com.yihukurama.sysbase.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihukurama.tkmybatisplus.framework.domain.tkmapper.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author: liyuan
 * @date: 2020/4/28 23:11
 * @description: 商品规格模型
 */
@ApiModel(value = "商品规格")
@Table(name = "tb_product_standard")
@Data
public class ProductstandardEntity extends BaseEntity {

    /**
     * 商品id
     **/
    @ApiModelProperty(value = "商品id")
    @Column(name = "product_id")
    private String productId;

    /**
     * 父规格名称,如 颜色
     **/
    @ApiModelProperty(value = "父规格名称,如 颜色")
    @Column(name = "standard_name")
    private String standardName;

    /**
     * 子规格列表,用分号分割,如红色;蓝色波纹;砖红色
     **/
    @ApiModelProperty(value = "子规格列表,用分号分割,如红色;蓝色波纹;砖红色")
    @Column(name = "child_list")
    private String childList;

    /**
     * 最后修改日期
     **/
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "operate_date")
    private Date operateDate;


    /**
     * 创建人id
     **/
    @ApiModelProperty(value = "创建人id")
    @Column(name = "creater_id")
    private String createrId;
    /**
     * 删除状态 0正常 1删除
     **/
    @ApiModelProperty(value = "删除状态 0正常 1删除")
    @Column(name = "is_delete")
    private Integer isDelete;
    /**
     * 最后修改人id
     **/
    @ApiModelProperty(value = "最后修改人id")
    @Column(name = "operator_id")
    private String operatorId;

    /**
     * 创建时间
     **/
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_date")
    private Date createDate;
}

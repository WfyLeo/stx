package com.ctx.exchange.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
    * 用户钱包地址信息
    */
@ApiModel(description="用户钱包地址信息")
@Data
@TableName(value = "user_address")
public class UserAddress implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户ID")
    private Long userId;

    /**
     * 币种ID
     */
    @TableField(value = "coin_id")
    @ApiModelProperty(value="币种ID")
    private Long coinId;

    /**
     * 地址
     */
    @TableField(value = "address")
    @ApiModelProperty(value="地址")
    private String address;

    /**
     * keystore
     */
    @TableField(value = "keystore")
    @ApiModelProperty(value="keystore")
    private String keystore;

    /**
     * 密码
     */
    @TableField(value = "pwd")
    @ApiModelProperty(value="密码")
    private String pwd;

    /**
     * 更新时间
     */
    @TableField(value = "last_update_time")
    @ApiModelProperty(value="更新时间")
    private Date lastUpdateTime;

    /**
     * 创建时间
     */
    @TableField(value = "created")
    @ApiModelProperty(value="创建时间")
    private Date created;

    @TableField(value = "markid")
    @ApiModelProperty(value="")
    private Long markid;

    private static final long serialVersionUID = 1L;
}
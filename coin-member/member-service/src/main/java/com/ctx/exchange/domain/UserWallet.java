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
    * 用户提币地址
    */
@ApiModel(description="用户提币地址")
@Data
@TableName(value = "user_wallet")
public class UserWallet implements Serializable {
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
     * 币种名称
     */
    @TableField(value = "coin_name")
    @ApiModelProperty(value="币种名称")
    private String coinName;

    /**
     * 提币地址名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="提币地址名称")
    private String name;

    /**
     * 地址
     */
    @TableField(value = "addr")
    @ApiModelProperty(value="地址")
    private String addr;

    /**
     * 排序
     */
    @TableField(value = "sort")
    @ApiModelProperty(value="排序")
    private Integer sort;

    /**
     * 状态
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value="状态")
    private Byte status;

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

    private static final long serialVersionUID = 1L;
}
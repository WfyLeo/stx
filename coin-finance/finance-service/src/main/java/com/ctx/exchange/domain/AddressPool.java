package com.ctx.exchange.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
    * 用户的地址池
    */
@ApiModel(description="用户的地址池")
@Data
@TableName(value = "address_pool")
public class AddressPool implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Long id;

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
     * 地址类型
     */
    @TableField(value = "coin_type")
    @ApiModelProperty(value="地址类型")
    private String coinType;

    private static final long serialVersionUID = 1L;
}
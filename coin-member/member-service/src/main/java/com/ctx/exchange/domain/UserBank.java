package com.ctx.exchange.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.FileFilter;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
    * 用户人民币提现地址
    */
@ApiModel(description="用户人民币提现地址")
@Data
@TableName(value = "user_bank")
public class UserBank implements Serializable {
    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="自增id")
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户id")
    private Long userId;

    /**
     * 银行卡名称
     */
    @TableField(value = "remark")
    @ApiModelProperty(value="银行卡名称")
    private String remark;

    /**
     * 开户人
     */
    @TableField(value = "real_name")
    @ApiModelProperty(value="开户人")
    private String realName;

    /**
     * 开户行
     */
    @TableField(value = "bank")
    @ApiModelProperty(value="开户行")
    private String bank;

    /**
     * 开户省
     */
    @TableField(value = "bank_prov")
    @ApiModelProperty(value="开户省")
    private String bankProv;

    /**
     * 开户市
     */
    @TableField(value = "bank_city")
    @ApiModelProperty(value="开户市")
    private String bankCity;

    /**
     * 开户地址
     */
    @TableField(value = "bank_addr")
    @ApiModelProperty(value="开户地址")
    private String bankAddr;

    /**
     * 开户账号
     */
    @TableField(value = "bank_card")
    @ApiModelProperty(value="开户账号")
    @NotBlank
    private String bankCard;

    /**
     * 状态：0，禁用；1，启用；
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value="状态：0，禁用；1，启用；")
    private Byte status;

    /**
     * 更新时间
     */
    @TableField(value = "last_update_time",fill = FieldFill.UPDATE)
    @ApiModelProperty(value="更新时间")
    private Date lastUpdateTime;

    /**
     * 创建时间
     */
    @TableField(value = "created",fill = FieldFill.INSERT)
    @ApiModelProperty(value="创建时间")
    private Date created;

    @ApiModelProperty(value = "交易密码")
    @TableField(exist = false)
    @NotBlank
    private String payPassword ;

    private static final long serialVersionUID = 1L;
}
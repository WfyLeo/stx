package com.ctx.exchange.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@ApiModel(description="user_account_freeze")
@Data
@TableName(value = "user_account_freeze")
public class UserAccountFreeze implements Serializable {
    @TableId(value = "user_id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Long userId;

    @TableField(value = "`freeze`")
    @ApiModelProperty(value="")
    private BigDecimal freeze;

    private static final long serialVersionUID = 1L;
}
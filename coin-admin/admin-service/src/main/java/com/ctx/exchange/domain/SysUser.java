package com.ctx.exchange.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
    * 平台用户
    */
@ApiModel(description="平台用户")
@Getter
@Setter
@TableName(value = "sys_user")
public class SysUser implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="主键")
    private Long id;

    /**
     * 账号
     */
    @TableField(value = "username")
    @ApiModelProperty(value="账号")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    @ApiModelProperty(value="密码")
    private String password;

    /**
     * 姓名
     */
    @TableField(value = "fullname")
    @ApiModelProperty(value="姓名")
    private String fullname;

    /**
     * 手机号
     */
    @TableField(value = "mobile")
    @ApiModelProperty(value="手机号")
    private String mobile;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    @ApiModelProperty(value="邮箱")
    private String email;

    /**
     * 状态 0-无效； 1-有效；
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value="状态 0-无效； 1-有效；")
    private Byte status;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    @ApiModelProperty(value="创建人")
    private Long createBy;

    /**
     * 修改人
     */
    @TableField(value = "modify_by")
    @ApiModelProperty(value="修改人")
    private Long modifyBy;

    /**
     * 创建时间
     */
    @TableField(value = "created")
    @ApiModelProperty(value="创建时间")
    private Date created;

    /**
     * 修改时间
     */
    @TableField(value = "last_update_time")
    @ApiModelProperty(value="修改时间")
    private Date lastUpdateTime;

    @ApiModelProperty(value = "角色的IDS")
    @TableField(exist = false)
    private String role_strings ;

    private static final long serialVersionUID = 1L;
}
package com.ctx.exchange.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
    * 角色权限配置
    */
@ApiModel(description="角色权限配置")
@Getter
@Setter
@TableName(value = "sys_role_privilege")
public class SysRolePrivilege implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Long id;

    @TableField(value = "role_id")
    @ApiModelProperty(value="")
    private Long roleId;

    @TableField(value = "privilege_id")
    @ApiModelProperty(value="")
    private Long privilegeId;

    private static final long serialVersionUID = 1L;
}
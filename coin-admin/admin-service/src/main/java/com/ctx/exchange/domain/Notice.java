package com.ctx.exchange.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
    * 系统资讯公告信息
    */
@ApiModel(description="系统资讯公告信息")
@Getter
@Setter
@TableName(value = "notice")
public class Notice implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Long id;

    /**
     * 标题
     */
    @TableField(value = "title")
    @ApiModelProperty(value="标题")
    @NotBlank
    private String title;

    /**
     * 简介
     */
    @TableField(value = "description")
    @ApiModelProperty(value="简介")
    @NotBlank
    private String description;

    /**
     * 作者
     */
    @TableField(value = "author")
    @ApiModelProperty(value="作者")
    @NotBlank
    private String author;

    /**
     * 文章状态
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value="文章状态")
    private Integer status;

    /**
     * 文章排序，越大越靠前
     */
    @TableField(value = "sort")
    @ApiModelProperty(value="文章排序，越大越靠前")
    @NotNull
    private Integer sort;

    /**
     * 内容
     */
    @TableField(value = "content")
    @ApiModelProperty(value="内容")
    @NotBlank
    private String content;

    /**
     * 最后修改时间
     */
    @TableField(value = "last_update_time",fill = FieldFill.UPDATE)
    @ApiModelProperty(value="最后修改时间")
    private Date lastUpdateTime;

    /**
     * 创建日期
     */
    @TableField(value = "created",fill = FieldFill.INSERT)
    @ApiModelProperty(value="创建日期")
    private Date created;

    private static final long serialVersionUID = 1L;
}
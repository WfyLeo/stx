package com.ctx.exchange.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
    * 工单记录
    */
@ApiModel(description="工单记录")
@Getter
@Setter
@TableName(value = "work_issue")
public class WorkIssue implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="主键")
    private Long id;

    /**
     * 用户id(提问用户id)
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户id(提问用户id)")
    private Long userId;

    /**
     * 回复人id
     */
    @TableField(value = "answer_user_id")
    @ApiModelProperty(value="回复人id")
    private Long answerUserId;

    /**
     * 回复人名称
     */
    @TableField(value = "answer_name")
    @ApiModelProperty(value="回复人名称")
    private String answerName;

    /**
     * 工单内容
     */
    @TableField(value = "question")
    @ApiModelProperty(value="工单内容")
    private String question;

    /**
     * 回答内容
     */
    @TableField(value = "answer")
    @ApiModelProperty(value="回答内容")
    private String answer;

    /**
     * 状态：1-待回答；2-已回答；
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value="状态：1-待回答；2-已回答；")
    private Boolean status;

    /**
     * 修改时间
     */
    @TableField(value = "last_update_time",fill = FieldFill.UPDATE)
    @ApiModelProperty(value="修改时间")
    private Date lastUpdateTime;

    /**
     * 创建时间
     */
    @TableField(value = "created",fill = FieldFill.INSERT)
    @ApiModelProperty(value="创建时间")
    private Date created;

    @TableField(exist = false)
    @ApiModelProperty(value = "创建工单的用户名称")
    public String username ="测试用户" ;

    @TableField(exist = false)
    @ApiModelProperty(value = "创建工单的用户真实名称")
    private String realName= "测试用户" ;

    private static final long serialVersionUID = 1L;
}
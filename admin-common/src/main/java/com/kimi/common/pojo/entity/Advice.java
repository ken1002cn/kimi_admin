package com.kimi.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("advice")
public class Advice {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("name")
    private String name;
    @TableField("advice")
    private String advice;
    @TableField("satisfaction")
    private Integer satisfaction;
    @TableField("feedbackReasonString")
    private String feedbackReasonString;
    @TableField(exist = false)
    private String[] feedbackReasons;
    @TableField("create_time")
    private Date createTime;
    @TableField("create_by")
    private String createBy;
    @TableField("update_time")
    private Date updateTime;
    @TableField("update_by")
    private String updateBy;
}

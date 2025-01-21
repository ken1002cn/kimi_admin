package com.kimi.blog.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TExamine {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("text")
    private String text;
    @TableField("articles_id")
    private Long articlesId;
    @TableField("articles_tittle")
    private Long articlesTittle;
    @TableField("reject_reason")
    private Integer rejectReason;
    @TableField("status")
    private Integer status;
    @TableField("create_time")
    private Date createTime;
    @TableField("create_by")
    private String createBy;
    @TableField("update_time")
    private Date updateTime;
    @TableField("update_by")
    private String updateBy;
}

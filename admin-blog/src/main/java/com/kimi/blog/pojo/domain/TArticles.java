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
public class TArticles {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("tittle")
    private String tittle;
    @TableField("text")
    private String text;
    @TableField("type")
    private Integer type;
    @TableField("hot")
    private Integer hot;
    @TableField("up")
    private Integer up;
    @TableField("down")
    private Integer down;
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

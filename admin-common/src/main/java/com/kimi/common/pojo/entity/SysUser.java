package com.kimi.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("Sys_user")
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("dept_id")
    private Long deptId;
    @TableField("nickname")
    private String nickname;
    @TableField("account")
    private String account;
    @TableField("password")
    private String password;
    @TableField("sex")
    private String sex;
    @TableField("phone")
    private String phone;
    @TableField("email")
    private String email;
    @TableField("status")
    private String status;
    @TableField("avatar")
    private  String avatar;
    @TableField("create_time")
    private Date createTime;
    @TableField("create_by")
    private String createBy;
    @TableField("update_time")
    private Date updateTime;
    @TableField("update_by")
    private String updateBy;
}

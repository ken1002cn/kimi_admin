package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysUser extends BaseEntity{
    private static final long serialVersionUID = 1L;
    private Long deptId;
    private String nickName;
    private String account;
    private String password;
    private String sex;
    private String phone;
    private String email;
    private String status;
    private  String avatar;
}

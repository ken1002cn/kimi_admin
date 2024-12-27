package com.kimi.common.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserListRespVo {
    private Long id;
    private String deptName;
    private String nickname;
    private String account;
    private String password;
    private String sex;
    private String phone;
    private String email;
    private String status;
    private String avatar;
    private Date createTime;
    private String roles;
}

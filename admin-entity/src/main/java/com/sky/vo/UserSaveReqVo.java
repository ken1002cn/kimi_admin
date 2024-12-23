package com.sky.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSaveReqVo {

    private Long id;
    private Long deptId;
    private String nickname;
    private String account;
    private String password;
    private String sex;
    private String phone;
    private String email;
    private String status;
    private  String avatar;
    private String roles;
}

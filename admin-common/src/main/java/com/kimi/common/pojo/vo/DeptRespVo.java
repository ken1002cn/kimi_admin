package com.kimi.common.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DeptRespVo {

    private Long id;
    private String deptName;
    private String parentDept;
    private Date createTime;
}

package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysRole extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String roleName;
    private String code;
}

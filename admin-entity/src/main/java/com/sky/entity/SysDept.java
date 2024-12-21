package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysDept extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String deptName;
    private Long parentId;
}

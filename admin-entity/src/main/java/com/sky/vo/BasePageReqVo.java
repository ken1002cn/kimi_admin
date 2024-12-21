package com.sky.vo;

import lombok.Data;

@Data
public class BasePageReqVo {
    private int pageNum;
    private int pageSize;
    private String orderBy;
    private String query;
}

package com.kimi.common.pojo.vo;

import lombok.Data;

import java.util.HashMap;

@Data
public class BasePageReqVo {
    private int pageNum;
    private int pageSize;
    private String orderBy;
    private HashMap<String,String> query;
}

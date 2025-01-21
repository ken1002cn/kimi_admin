package com.kimi.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimi.blog.mapper.ExamineMapper;
import com.kimi.blog.pojo.domain.TArticles;
import com.kimi.blog.pojo.domain.TExamine;
import com.kimi.blog.service.ExamineService;
import com.kimi.common.constant.ExamineConstant;

import java.util.Date;

public class ExamineServiceImpl extends ServiceImpl<ExamineMapper, TExamine> implements ExamineService {


    @Override
    public void createExamine(TArticles tArticles) {
        TExamine tExamine = TExamine.builder()
                .articlesId(tArticles.getId())
                .articlesTittle(tArticles.getTittle())
                .status(ExamineConstant.CREATE)
                .createTime(new Date())
                .createBy("系统")
                .build();
    }
}

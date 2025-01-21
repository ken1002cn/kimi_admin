package com.kimi.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimi.blog.mapper.ArticlesMapper;
import com.kimi.blog.pojo.domain.TArticles;
import com.kimi.blog.service.ArticlesService;
import com.kimi.blog.service.ExamineService;
import com.kimi.common.constant.ArticlesStatusConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ArticlesServiceImpl extends ServiceImpl<ArticlesMapper, TArticles> implements ArticlesService {
    @Autowired
    private ExamineService examineService;
    @Override
    public boolean createArticles(TArticles articles) {
        articles.setCreateTime(new Date());
        articles.setStatus(ArticlesStatusConstant.ARTICLES_STATUS_INACTIVE);
        baseMapper.insert(articles);
        examineService.createExamine(articles);
        return true;
    }
}

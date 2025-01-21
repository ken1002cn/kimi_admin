package com.kimi.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimi.blog.mapper.ArticlesMapper;
import com.kimi.blog.pojo.domain.TArticles;
import com.kimi.blog.service.ArticlesService;
import org.springframework.stereotype.Service;

@Service
public class ArticlesServiceImpl extends ServiceImpl<ArticlesMapper, TArticles> implements ArticlesService {
    @Override
    public void createArticles(TArticles articles) {

    }
}

package com.kimi.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kimi.blog.pojo.domain.TArticles;

public interface ArticlesService extends IService<TArticles> {
    boolean createArticles(TArticles articles);
}

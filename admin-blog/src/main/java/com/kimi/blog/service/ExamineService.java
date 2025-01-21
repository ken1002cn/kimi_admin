package com.kimi.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kimi.blog.pojo.domain.TArticles;
import com.kimi.blog.pojo.domain.TExamine;
import org.springframework.stereotype.Service;

@Service
public interface ExamineService extends IService<TExamine> {
    void createExamine(TArticles tArticles);
}

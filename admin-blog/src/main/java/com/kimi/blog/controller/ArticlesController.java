package com.kimi.blog.controller;

import com.kimi.blog.pojo.domain.TArticles;
import com.kimi.blog.service.ArticlesService;
import com.kimi.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/articles/")
public class ArticlesController {
    @Autowired
    private ArticlesService articlesService;

    @GetMapping("list")
    public Result<List<TArticles>> list() {
        List<TArticles> list = articlesService.list();
        return Result.success(list);
    }
    @PostMapping("create")
    public Result create(@RequestBody TArticles articles) {
        articlesService.createArticles(articles);
        return Result.success();
    }
}

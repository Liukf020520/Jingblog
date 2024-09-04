package com.liukf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liukf.domain.ResponseResult;
import com.liukf.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);
}

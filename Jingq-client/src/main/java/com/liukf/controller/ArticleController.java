package com.liukf.controller;

import com.liukf.domain.ResponseResult;
import com.liukf.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

//    @GetMapping("/list")
//    public List<Article> test(){
//        return articleService.list();
//    }
    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){
        //查询热门文章
        ResponseResult result = articleService.hotArticleList();
        return result;
    }

    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId){
        //查询文章分页
        ResponseResult result = articleService.articleList(pageNum,pageSize,categoryId);
        return result;
    }
    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
       return articleService.getArticleDetail(id);
    }
}
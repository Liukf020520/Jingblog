package com.liukf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liukf.constants.SystemConstants;
import com.liukf.domain.ResponseResult;
import com.liukf.domain.entity.Category;
import com.liukf.domain.vo.ArticleDetaListVo;
import com.liukf.domain.vo.ArticleListVo;
import com.liukf.domain.vo.HotArticleVo;
import com.liukf.domain.entity.Article;
import com.liukf.domain.vo.PageVo;
import com.liukf.mapper.ArticleMapper;
import com.liukf.service.ArticleService;

import com.liukf.utils.BeanCopyUtls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
     @Autowired
     private CategoryServiceImpl categoryService;
     @Override
    public ResponseResult hotArticleList() {
        //查询热门文章，封装成ResponseResult返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //必须是正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //按照浏览量排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //限制查询10条
        Page<Article> page = new Page(1,10);
        page(page,queryWrapper);

        List<Article> articles = page.getRecords();
        //BeanCopy
        List<HotArticleVo> vs = BeanCopyUtls.copyBeanList(articles,HotArticleVo.class);
        return ResponseResult.okResult(vs);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //如果有categoryId，则查询时要和传入的id比较
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId);
        //状态是正式的
        lambdaQueryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        //对isTop进行降序排序
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);
        //分页查询
        Page<Article>page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);
        //查询分类名称
        List<Article> articles = page.getRecords();
        //articlesid去查询分类名
        for (Article article : articles){
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
        }
        //封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtls.copyBeanList(page.getRecords(), ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
         //根据id查询文章
        Article article = getById(id);
        //转换成VO
        ArticleDetaListVo articleDetaListVo = BeanCopyUtls.copyBean(article, ArticleDetaListVo.class);
        //根据分类id查询分类名
        Long categoryId = articleDetaListVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if (category!=null){
            articleDetaListVo.setCategoryName(category.getName());
        }
        //封装响应返回
        return ResponseResult.okResult(articleDetaListVo);
    }
}
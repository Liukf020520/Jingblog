package com.liukf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liukf.domain.ResponseResult;
import com.liukf.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2024-08-29 11:16:51
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}


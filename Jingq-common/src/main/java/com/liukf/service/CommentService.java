package com.liukf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liukf.domain.ResponseResult;
import com.liukf.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2024-09-04 17:25:00
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize);
}


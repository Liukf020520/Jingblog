package com.liukf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liukf.constants.SystemConstants;
import com.liukf.domain.ResponseResult;
import com.liukf.domain.entity.Comment;
import com.liukf.domain.vo.CommentVo;
import com.liukf.domain.vo.PageVo;
import com.liukf.mapper.CommentMapper;
import com.liukf.service.CommentService;
import com.liukf.utils.BeanCopyUtls;
import kotlin.jvm.internal.Lambda;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2024-09-04 17:25:00
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        //查询对应文章根评论

        //对articleId进行判断
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId,articleId);
        //根评论的rootId为-1
        queryWrapper.eq(Comment::getRootId, SystemConstants.ROOT_COMMENT);
        //分页查询
        Page<Comment> page = new Page(pageNum, pageSize);
        page(page,queryWrapper);
        List<CommentVo> commentVoList = BeanCopyUtls.copyBeanList(page.getRecords(),CommentVo.class);
        return ResponseResult.okResult(new PageVo(commentVoList,page.getTotal()));
    }
}


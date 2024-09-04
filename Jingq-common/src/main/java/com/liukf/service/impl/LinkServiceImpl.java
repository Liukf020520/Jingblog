package com.liukf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liukf.constants.SystemConstants;
import com.liukf.domain.ResponseResult;
import com.liukf.domain.entity.Link;
import com.liukf.domain.vo.LinkVo;
import com.liukf.mapper.LinkMapper;
import com.liukf.service.LinkService;
import com.liukf.utils.BeanCopyUtls;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2024-09-02 15:46:42
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {
        // 查询所有审核通过的
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> list = list(queryWrapper);
        //转换vo
        List<LinkVo> linkVos = BeanCopyUtls.copyBeanList(list, LinkVo.class);
        return ResponseResult.okResult(linkVos);
    }
}


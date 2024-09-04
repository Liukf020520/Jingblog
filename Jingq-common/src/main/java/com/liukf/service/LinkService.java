package com.liukf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liukf.domain.ResponseResult;
import com.liukf.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2024-09-02 15:46:42
 */
public interface LinkService extends IService<Link> {
     ResponseResult getAllLink() ;
}


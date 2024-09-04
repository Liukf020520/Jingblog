package com.liukf.service.impl;

import com.liukf.domain.ResponseResult;
import com.liukf.domain.entity.LonginUser;
import com.liukf.domain.entity.User;
import com.liukf.domain.vo.BlogUserLoginVo;
import com.liukf.domain.vo.UserInfoVo;
import com.liukf.service.BlogLoginService;
import com.liukf.utils.BeanCopyUtls;
import com.liukf.utils.JwtUtil;
import com.liukf.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断认证是否通过
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userId 生成token
        LonginUser loginUser = (LonginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject("bloglogin:"+userId,loginUser);
        //把token和用户信息封装返回
        //把User转换成UserInfoVo
        UserInfoVo UserInfoVo = BeanCopyUtls.copyBean(loginUser.getUser(), UserInfoVo.class);
        BlogUserLoginVo vo = new BlogUserLoginVo(jwt,UserInfoVo);

        return ResponseResult.okResult(vo);
    }
}

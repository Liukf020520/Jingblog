package com.liukf.controller;

import com.liukf.domain.ResponseResult;
import com.liukf.domain.entity.User;
import com.liukf.enums.AppHttpCodeEnum;
import com.liukf.exeception.SystemException;
import com.liukf.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){

            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }


        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }
}

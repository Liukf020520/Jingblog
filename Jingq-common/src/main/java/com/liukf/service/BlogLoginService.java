package com.liukf.service;

import com.liukf.domain.ResponseResult;
import com.liukf.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);
}

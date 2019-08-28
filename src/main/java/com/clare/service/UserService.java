package com.clare.service;

import com.clare.mapper.UserMapper;
import com.clare.po.User;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CommonsLog
public class UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 获取用户登陆列表
     * @author zhanghao
     * @date 2019/8/23 18:46
     * @return
    **/
    public List<User> getUserInfoList() {
        List<User> users = userMapper.selectAll();
        int a=10/0;
        return users;
    }
}

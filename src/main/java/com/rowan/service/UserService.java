package com.rowan.service;

import com.rowan.core.dao.DataSource;
import com.rowan.mapper.UserMapper;
import com.rowan.po.User;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户信息
 *
 * @author zhanghao
 * @date 2019/9/26 18:05
 **/
@Service
@CommonsLog
@DataSource("technology")
public class UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 获取用户登陆列表
     *
     * @return
     * @author zhanghao
     * @date 2019/8/23 18:46
     **/
    public List<User> getUserInfoList() {
        List<User> users = userMapper.selectAll();
        return users;
    }

    public int saveUserInfo() {
        User user = new User();
        user.setName("zhanghao");
        return userMapper.insert(user);
    }
}

package com.rowan.mapper;

import com.rowan.core.common.MybatisBaseMapper;
import com.rowan.model.po.User;

import java.util.Map;

/**
 * @author : zhangHao
 * @data : 2019-07-31 19:28
 */
public interface UserMapper extends MybatisBaseMapper<User> {

    /**
     * 通过条件获取用户
     *
     * @param params
     * @return
     */
    User selectUserInfoByMap(Map<String, Object> params);
}
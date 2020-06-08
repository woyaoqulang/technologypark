package com.rowan.mapper;

import com.rowan.core.model.MybatisBaseMapper;
import com.rowan.po.User;
import org.springframework.stereotype.Repository;

/**
 * @author : zhangHao
 * @data : 2019-07-31 19:28
 */
@Repository
public interface UserMapper extends MybatisBaseMapper<User> {
}
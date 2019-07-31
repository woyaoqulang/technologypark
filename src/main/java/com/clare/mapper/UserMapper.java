package com.clare.mapper;

import com.clare.core.mapper.MybatisBaseMapper;
import com.clare.po.User;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author : zhangHao
 * @data : 2019-07-31 19:28
*/
@Repository
public interface UserMapper extends MybatisBaseMapper<User> {
}
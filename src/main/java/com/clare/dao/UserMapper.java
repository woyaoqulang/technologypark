package com.clare.dao;

import com.clare.pojo.User;
import java.util.List;
/**
 * @Author : zhangHao
 * @Date : 2019-07-09 19:53
 * @Description :
 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}

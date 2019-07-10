package com.clare.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author : zhangHao
 * @Date : 2019-07-09 19:54
 * @Description :
 */
@Table
public class User {

    @Id
    private Integer id;

    private String username;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

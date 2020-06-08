package com.rowan.Dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @description: 用户表
 * @author: rowan
 * @date: 2020/6/8 15:14
 **/
@Data
public class UserDto {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "注册途径")
    private Integer source;

    @ApiModelProperty(value = "身份")
    private Integer role;
}
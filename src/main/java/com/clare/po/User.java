package com.clare.po;

import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.Table;
import lombok.Data;

/**
 * 用户表
 * @author : zhangHao
 * @data : 2019-07-31 19:28
*/
@Data
@Table
public class User {
    @ApiModelProperty(value = "主键id")
    private Integer id;

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

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "注册途径")
    private Integer source;

    @ApiModelProperty(value = "身份")
    private Integer role;
}
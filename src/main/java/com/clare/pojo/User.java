package com.clare.pojo;

import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.Table;
import lombok.Data;

/**
 * 
 * @author : zhangHao
 * @data : 2019-07-18 20:11
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
}
package com.rowan.model.po;

import java.util.Date;

import com.rowan.core.model.BaseLongEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 目录表(Catalog)表服务接口
 * @author: rowan
 * @date: 2020-06-23 20:00:39
 **/
@Data
public class Catalog extends BaseLongEntity {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "目录名称")
    private String catalogName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建用户")
    private String createUser;

    @ApiModelProperty(value = "修改人")
    private String updateUser;

    @ApiModelProperty(value = "数据状态，0删除，1正常")
    private Boolean isDelete;

}
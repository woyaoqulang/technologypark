package com.rowan.core.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 基础公共实体类-使用一个父类来封装这些通用属性
 *
 * @author zhangHao
 * @date 2019-07-18 19:39
 */
@MappedSuperclass
public abstract class BaseLongEntity implements Serializable {
    private static final long serialVersionUID = 4960281194861384485L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

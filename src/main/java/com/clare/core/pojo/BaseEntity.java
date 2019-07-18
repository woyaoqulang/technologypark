package com.clare.core.pojo;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 基础公共实体类
 * @author zhangHao
 * @date 2019-07-18 19:39
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 4960281194861384485L;
}

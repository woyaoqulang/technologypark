package com.rowan.core.web;

import com.github.pagehelper.PageHelper;
import com.rowan.core.common.PageInfo;

/**
 * 分页处理
 *
 * @author zhanghao
 * @date 2019/9/26 20:05
 **/
public class ZhPageHelper extends PageHelper {

    public static final int defaultPageSize = 20;

    public ZhPageHelper() {
    }

    public static void startPage() {
        PageInfo<?> pageInfo = RequestContext.getPageInfo();
        if (pageInfo.getPageNo() <= 0 && pageInfo.getPageSize() <= 0) {
            startPage(1, 20, true);
        } else if (pageInfo.getPageSize() <= 0) {
            startPage(pageInfo.getPageNo(), 20, true);
        } else {
            startPage(pageInfo.getPageNo(), pageInfo.getPageSize(), true);
        }

    }

    public static void startPage(Integer pageNo) {
        PageInfo<?> pageInfo = RequestContext.getPageInfo();
        if (pageInfo.getPageSize() <= 0) {
            startPage(pageNo, 20, true);
        } else {
            startPage(pageNo, pageInfo.getPageSize(), true);
        }

    }
}

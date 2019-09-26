package com.clare.core.web;

import com.clare.core.model.PageInfo;
import com.github.pagehelper.PageHelper;
/**
 * 分页处理
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

    public static void startPage(int pageNo) {
        PageInfo<?> pageInfo = RequestContext.getPageInfo();
        if (pageInfo.getPageSize() <= 0) {
            startPage(pageNo, 20, true);
        } else {
            startPage(pageNo, pageInfo.getPageSize(), true);
        }

    }
}

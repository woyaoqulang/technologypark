package com.clare.core.model;


import com.clare.core.exception.BusinessException;
import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.apachecommons.CommonsLog;

import java.util.List;

/**
 * 分页对象
 *
 * @author zhanghao
 * @date 2019/9/26 17:04
 **/
@ApiModel("分页对象")
@CommonsLog
public class PageInfo<T> {

    @ApiModelProperty("当前页,默认为第一页")
    private int pageNo;
    @ApiModelProperty("每页的数量,默认每页20条")
    private int pageSize;
    @ApiModelProperty("当前页的数量")
    private int currentPageSize;
    @ApiModelProperty("当前页面第一个元素在数据库中的行号")
    private int startRow;
    @ApiModelProperty("当前页面最后一个元素在数据库中的行号")
    private int endRow;
    @ApiModelProperty("总记录数")
    private long totalNum;
    @ApiModelProperty("总页数")
    private int pageNum;
    @ApiModelProperty("结果集")
    private List<T> list;
    @ApiModelProperty("第一页")
    private int firstPage;
    @ApiModelProperty("前一页")
    private int prePage;
    @ApiModelProperty("下一页")
    private int nextPage;
    @ApiModelProperty("最后一页")
    private int lastPage;
    @ApiModelProperty("是否为第一页")
    private boolean isFirstPage;
    @ApiModelProperty("是否为最后一页")
    private boolean isLastPage;
    @ApiModelProperty("是否有前一页")
    private boolean hasPreviousPage;
    @ApiModelProperty("是否有下一页")
    private boolean hasNextPage;
    @ApiModelProperty("导航页码数")
    private int navigatePages;
    @ApiModelProperty("所有导航页号")
    private int[] navigatepageNums;

    private void calPageInfo(List<T> resultList, int navigatePages) {
        Page page = null;

        try {
            page = (Page) resultList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("不是分页结果集，无法计算");
        }

        this.pageNo = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.totalNum = page.getTotal();
        this.pageNum = page.getPages();
        this.list = page;
        this.currentPageSize = page.size();
        this.startRow = page.getStartRow() + 1;
        // 计算实际的endRow（最后一页的时候特殊）
        this.endRow = this.startRow - 1 + this.currentPageSize;
        this.navigatePages = navigatePages;
        // 计算导航页
        this.calcNavigatepageNums();
        // 计算前后页，第一页，最后一页
        this.calcPage();
        // 判断页面边界
        this.judgePageBoudary();
    }

    private void calcNavigatepageNums() {
        // 当总页数小于或等于导航页码数时
        if (pageNum <= navigatePages) {
            navigatepageNums = new int[pageNum];
            for (int i = 0; i < pageNum; i++) {
                navigatepageNums[i] = i + 1;
            }
        } else {
            navigatepageNums = new int[navigatePages];
            int startNum = pageNo - navigatePages / 2;
            int endNum = pageNo + navigatePages / 2;
            if (startNum < 1) {
                startNum = 1;
                // (最前navigatePages页
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }
            } else if (endNum > pageNum) {
                endNum = pageNum;
                // 最后navigatePages页
                for (int i = navigatePages - 1; i >= 0; i--) {
                    navigatepageNums[i] = endNum--;
                }
            } else {
                // 所有中间页
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }
            }
        }

    }

    /**
     * 计算前后页，第一页，最后一页
     */
    private void calcPage() {
        if (navigatepageNums != null && navigatepageNums.length > 0) {
            firstPage = navigatepageNums[0];
            lastPage = navigatepageNums[navigatepageNums.length - 1];
            if (pageNo > 1) {
                prePage = pageNo - 1;
            }
            if (pageNo < pageNum) {
                nextPage = pageNo + 1;
            }
        }
    }

    /**
     * 判定页面边界
     */
    private void judgePageBoudary() {
        isFirstPage = pageNo == 1;
        isLastPage = pageNo == pageNum && pageNo != 1;
        hasPreviousPage = pageNo > 1;
        hasNextPage = pageNo < pageNum;
    }

    public void setResultList(List<T> resultList) {
        this.calPageInfo(resultList, 8);
    }

    public void setResultList(List<T> resultList, int navigatePages) {
        this.calPageInfo(resultList, navigatePages);
    }

    public PageInfo() {
        this.pageNo = 1;
        this.pageSize = 20;
        this.isFirstPage = false;
        this.isLastPage = false;
        this.hasPreviousPage = false;
        this.hasNextPage = false;
    }

    public PageInfo(List<T> resultList) {
        this(resultList, 8);
    }

    public PageInfo(List<T> resultList, int navigatePages) {
        this.pageNo = 1;
        this.pageSize = 20;
        this.isFirstPage = false;
        this.isLastPage = false;
        this.hasPreviousPage = false;
        this.hasNextPage = false;
        this.calPageInfo(resultList, navigatePages);
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getCurrentPageSize() {
        return currentPageSize;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public long getTotalNum() {
        return totalNum;
    }

    public int getPageNum() {
        return pageNum;
    }

    public List<T> getList() {
        return list;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public int getPrePage() {
        return prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public int[] getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}

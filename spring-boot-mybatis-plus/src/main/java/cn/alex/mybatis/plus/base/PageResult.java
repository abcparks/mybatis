package cn.alex.mybatis.plus.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author:nixiaowei
 * Date:2025-12-04
 */
@Data
public class PageResult<T> {

    /*
     * 当前页码
     */
    private long current = 1;

    /*
     * 每页显示条数
     */
    private long size = 10;

    /*
     * 总共多少页
     */
    private long pages;

    /*
     * 总数
     */
    private long total;

    /**
     * 结果集
     */
    private List<T> records = new ArrayList<>();

    /**
     * 排序
     * @param comparator 比较器
     * @return this
     */
    public PageResult<T> startSort(Comparator<T> comparator) {
        if (comparator != null) {
            records.sort(comparator);
        }
        return this;
    }

    public PageResult<T> startPage(long pageNum, long pageSize) {
        // 当前页码
        if (pageNum > 0) {
            this.current = pageNum;
        }
        // 每页显示条数
        if (pageSize > 0) {
            this.size = pageSize;
        }
        // 总数
        this.total = this.records.size();
        // 总共多少页
        this.pages = (this.total - 1) / this.size + 1;
        // 结果集 (stream skip 左闭右开)
        if (this.records.size() > pageSize) {
            this.records = this.records.stream().skip((this.current - 1) * this.size).limit(this.size).collect(Collectors.toList());
        }
        return this;
    }

    public PageResult<T> startPage(PageParam pageParam) {
        return this.startPage(pageParam.getPageNum(), pageParam.getPageSize());
    }

    public static <T> PageResult<T> restResult(IPage<T> iPage) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setRecords(iPage.getRecords());
        pageResult.setPages(iPage.getPages());
        pageResult.setTotal(iPage.getTotal());
        pageResult.setSize(iPage.getSize());
        pageResult.setCurrent(iPage.getCurrent());
        return pageResult;
    }

}

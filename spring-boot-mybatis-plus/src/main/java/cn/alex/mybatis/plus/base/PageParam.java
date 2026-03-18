package cn.alex.mybatis.plus.base;

import lombok.Data;

/**
 * Author:nixiaowei
 * Date:2025-12-04
 * <p>
 * 分页请求
 */
@Data
public class PageParam {

    /**
     * 当前页码
     */
    protected int pageNum = 1;

    /**
     * 每页显示条数
     */
    protected int pageSize = 10;

}

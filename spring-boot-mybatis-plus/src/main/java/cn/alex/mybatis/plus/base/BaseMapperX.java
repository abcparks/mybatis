package cn.alex.mybatis.plus.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface BaseMapperX<T> extends BaseMapper<T> {

    /**
     * 分页查询
     * @param pageParam 分页参数
     * @param wrapper   查询参数
     * @return 分页结果
     */
    default PageResult<T> selectPage(PageParam pageParam, Wrapper<T> wrapper) {
        Page<T> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());
        this.selectPage(page, wrapper);
        return PageResult.restResult(page);
    }

}

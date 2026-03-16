package cn.alex.mybatis.plus.mapper;

import cn.alex.mybatis.plus.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by WCY on 2021/4/5
 */

// 在对应得Mapper上面继承基本的类BaseMapper
@Mapper
public interface UserMapper extends BaseMapper<User> {
    // 所有的crud操作都已经编写完成, 不需要像以前的配置一大堆文件

    /**
     * 根据用户名查询用户
     * @param name 用户名
     * @return 用户
     */
    List<User> selectByName(@Param("name") String name);

    Page<User> selectPageVO(@Param("page") Page<User> page, @Param("age") Integer age);

}

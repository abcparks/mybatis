package cn.alex.mybatis.plus.service;

import cn.alex.mybatis.plus.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by WCY on 2021/4/5
 */
public interface UserService extends IService<User> {

    List<User> selectByName(@Param("name")String name);

}

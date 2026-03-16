package cn.alex.transactional.mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by WCY on 2022/5/16
 */
public interface UserMapper {

    List<Map<String, Object>> findAllUser();

    void updateUser();
}

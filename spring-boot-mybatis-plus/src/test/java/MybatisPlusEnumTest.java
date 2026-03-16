import cn.alex.mybatis.plus.MybatisPlusApplication;
import cn.alex.mybatis.plus.domain.User;
import cn.alex.mybatis.plus.enums.SexEnum;
import cn.alex.mybatis.plus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by WCY on 2022/3/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisPlusApplication.class)
public class MybatisPlusEnumTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void insertEnum() {
        User user = new User();
        user.setName("admin");
        user.setAge(33);
        user.setSex(SexEnum.MALE);
        int result = userMapper.insert(user);
        System.out.println("result = " + result);
    }
}

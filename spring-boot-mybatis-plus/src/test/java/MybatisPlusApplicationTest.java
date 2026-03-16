import cn.alex.mybatis.plus.MybatisPlusApplication;
import cn.alex.mybatis.plus.domain.User;
import cn.alex.mybatis.plus.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by WCY on 2021/8/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisPlusApplication.class)
public class MybatisPlusApplicationTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void selectAll() {
        // 查询全部用户
        // 参数是一个Wrapper, 条件构造器
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByName() {
        // 查询全部用户
        // 参数是一个Wrapper, 条件构造器
        List<User> users = userMapper.selectByName("Tom");
        System.out.println("users = " + users);
    }

    /**
     * 插入测试
     */
    @Test
    public void insertUser() {
        User user = new User();
        user.setName("Jerry");
        user.setAge(3);
        user.setEmail("wcy401814@163.com");
        int result = userMapper.insert(user); // mybatis-plus会自动生成id
        System.out.println("result = " + result);
        System.out.println("user = " + user); // id会自动回填
    }

    @Test
    public void deleteUser() {
        User user = new User();
        user.setId(3L);
        //user.setName("Billie");
        int result = userMapper.deleteById(user);
        System.out.println("result = " + result);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select();
        userMapper.delete(wrapper);

    }

    /**
     * 更新测试
     */
    @Test
    public void updateUser() {
        User user = new User();
        user.setId(1379034644065914887L);
        user.setName("Tomcat");
        user.setAge(26);
        //user.setEmail("1");
        int result = userMapper.updateById(user);
        System.out.println("result = " + result);
        System.out.println("user = " + user);
    }
}

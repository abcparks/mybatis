import cn.alex.mybatis.plus.MybatisPlusApplication;
import cn.alex.mybatis.plus.domain.User;
import cn.alex.mybatis.plus.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by WCY on 2022/3/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisPlusApplication.class)
public class MybatisPlusWrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectByWrapper() {
        // 查询用户名包含A, 年龄在20到30之间
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name", "a")
                .between("age", 20, 25)
                .isNotNull("email");
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectUserOrderByAgeDesc() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("age")
                .orderByAsc("id");
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void deleteEmailIsNull() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNull("email");
        int result = userMapper.delete(wrapper);
        System.out.println("result = " + result);
    }

    @Test
    public void updateByWrapper() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age", 25)
                .like("name", "a")
                .or()
                .isNull("email");
        User user = new User();
        user.setAge(30);
        user.setEmail("wcy401814@163.com");
        int result = userMapper.update(user, wrapper);
        System.out.println("result = " + result);
    }

    @Test
    public void updateByWrapper2() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name", "a")
                .and(i -> i.gt("age", "25").or().isNull("email"));
        User user = new User();
        user.setAge(35);
        user.setEmail("wx401814@163.com");
        int result = userMapper.update(user, wrapper);
        System.out.println("result = " + result);
    }

    @Test
    public void select() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("name", "age", "id");
        List<Map<String, Object>> userList = userMapper.selectMaps(wrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void subSelect() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.inSql("id", "select id from t_user where id <= 5");
        List<Map<String, Object>> userList = userMapper.selectMaps(wrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void updateWrapper() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.like("name", "a")
                .and(i -> i.gt("age", 25).or().isNull("email"));
        wrapper.set("name", "青青");
        int result = userMapper.update(null, wrapper);
        System.out.println("result = " + result);
    }

    @Test
    public void selectLike() {
        String name = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("name", name);
        }
        if (ageBegin != null) {
            wrapper.gt("age", ageBegin);
        }

        if (ageEnd != null) {
            wrapper.lt("age", ageEnd);
        }
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectLike2() {
        String name = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), "name", name)
                .ge(ageBegin != null, "age", ageBegin)
                .le(ageEnd != null, "age", ageEnd);
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void lambdaSelect() {
        String name = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), User::getName, name)
                .ge(ageBegin != null, User::getAge, ageBegin)
                .le(ageEnd != null, User::getAge, ageEnd);
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void lambdaUpdate() {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.like(User::getName, "a")
                .and(i -> i.gt(User::getAge, 30).or().isNull(User::getEmail));
        wrapper.set(User::getName, "青青");
        int result = userMapper.update(null, wrapper);
        System.out.println("result = " + result);
    }
}

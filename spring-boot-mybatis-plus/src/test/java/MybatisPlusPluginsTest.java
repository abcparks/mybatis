import cn.alex.mybatis.plus.MybatisPlusApplication;
import cn.alex.mybatis.plus.domain.User;
import cn.alex.mybatis.plus.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public class MybatisPlusPluginsTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void selectPage() {
        Page<User> page = new Page<>(2, 3);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        userMapper.selectPage(page, wrapper);
        System.out.println("page = " + page);
    }

    @Test
    public void selectPage2() {
        Page<User> page = new Page<>(2, 3);
        userMapper.selectPageVO(page, 35);
        System.out.println("page = " + page);
    }

}

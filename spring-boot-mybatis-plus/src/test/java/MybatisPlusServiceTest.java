import cn.alex.mybatis.plus.MybatisPlusApplication;
import cn.alex.mybatis.plus.domain.User;
import cn.alex.mybatis.plus.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WCY on 2022/3/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisPlusApplication.class)
public class MybatisPlusServiceTest {
    @Resource
    private UserService userService;

    @Test
    public void selectCount() {
        long count = userService.count();
        System.out.println("count = " + count);
    }

    @Test
    public void batchInsert() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("AI" + i);
            user.setAge(20 + i);
            list.add(user);
        }
        boolean flag = userService.saveBatch(list);
        System.out.println("flag = " + flag);
    }
}

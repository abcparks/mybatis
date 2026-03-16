import cn.alex.mybatis.MybatisApplication;
import cn.alex.mybatis.domain.User;
import cn.alex.mybatis.mapper.LockMapper;
import cn.alex.mybatis.mapper.UserMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by WCY on 2021/8/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisApplication.class)
public class MybatisApplicationTest {
    @Resource
    private UserMapper userMapper;

    @Autowired
    private LockMapper lockMapper;

    @Test
    public void log() {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.trace("trace 日志");
        logger.debug("debug 日志");
        logger.info("info 日志");
        logger.warn("warn 日志");
        logger.error("error 日志");
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            StackTraceElement[] StackTraceElements = e.getStackTrace();
            if (StackTraceElements != null && StackTraceElements.length > 0) {
                StackTraceElement stackTraceElement = StackTraceElements[0];
                System.out.println("className: " + stackTraceElement.getClassName());
                System.out.println("methodName: " + stackTraceElement.getMethodName());
                System.out.println("lineNumber: " + stackTraceElement.getLineNumber());
            }
            e.printStackTrace();
        }
    }

    @Test
    public void selectAllUser() {
        List<User> userList = userMapper.findAllUser();
        System.out.println("userList = " + userList);

       // List<Map<String, Object>> manyList = lockMapper.getOne2Many();

        Page page = PageHelper.startPage(1, 1).doSelectPage(() -> {
            lockMapper.getOne2Many();
        });

        PageInfo pageInfo = page.toPageInfo();
        List list = pageInfo.getList();
        System.out.println(list);
    }
}

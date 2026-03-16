import cn.alex.dynamic.datasource.DynamicDatasourceApplication;
import cn.alex.dynamic.datasource.domain.Teacher;
import cn.alex.dynamic.datasource.service.TeacherService;
import cn.alex.dynamic.datasource.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by WCY on 2022/8/31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DynamicDatasourceApplication.class)
public class DynamicDatasourceApplicationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void selectAllUser() {
        List<Map<String, Object>> selectUserList = userService.selectAllUser();
        List<Map<String, Object>> findUserList = userService.findAllUser();
        System.out.println("userList = " + selectUserList);
        System.out.println("findUserList = " + findUserList);
    }

    @Autowired
    private TeacherService teacherService;

    @Test
    public void dynamicDatasourceTransaction() {
        Teacher teacher = new Teacher();
        teacher.setName("吴福气");
        teacher.setCourse("政治思想建设");
        teacher.setAddress("南京雨花台");
        teacher.setBirth(new Date());
        //teacherService.mysqlInsertTeacher(teacher);
        //teacherService.oracleInsertTeacher(teacher);
        teacherService.insertTeacher(teacher);
    }

}

import cn.alex.multiple.datasource.MultipleDatasourceApplication;
import cn.alex.multiple.datasource.domain.Teacher;
import cn.alex.multiple.datasource.mapper.primary.UserMapper;
import cn.alex.multiple.datasource.mapper.secondary.BookMapper;
import cn.alex.multiple.datasource.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by WCY on 2022/8/31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MultipleDatasourceApplication.class)
public class MultipleDatasourceApplicationTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void multipleDatasource() throws SQLException {
        List<Map<String, Object>> userList = userMapper.findAllUser();
        System.out.println("userList = " + userList);

        List<Map<String, Object>> bookList = bookMapper.findAllBook();
        System.out.println("bookList = " + bookList);

        List<Map<String, Object>> mapList = bookMapper.selectAllBook();
        System.out.println("mapList = " + mapList);
    }

    @Autowired
    private TeacherService teacherService;

    @Test
    public void insertTeacher() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setName("吴福气");
        teacher.setCourse("政治思想建设");
        teacher.setAddress("南京雨花台");
        teacher.setBirth(new Date());
        teacherService.insertTeacher(teacher);
    }

    @Test
    public void multipleDatasourceTransaction() {
        Teacher teacher = new Teacher();
        teacher.setName("吴福气");
        teacher.setCourse("政治思想建设");
        teacher.setAddress("南京雨花台");
        teacher.setBirth(new Date());
        //teacherService.programmaticTransactionByManager(teacher);
        //teacherService.programmaticTransactionByTemplate(teacher);
        teacherService.declarativeTransaction(teacher);
        //teacherService.declarativeTransactionByStep(teacher);
    }

}

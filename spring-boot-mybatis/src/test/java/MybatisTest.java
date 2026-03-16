import cn.alex.mybatis.domain.Employee;
import cn.alex.mybatis.domain.Key;
import cn.alex.mybatis.domain.Lock;
import cn.alex.mybatis.domain.Teacher;
import cn.alex.mybatis.mapper.EmployeeMapper;
import cn.alex.mybatis.mapper.KeyMapper;
import cn.alex.mybatis.mapper.LockMapper;
import cn.alex.mybatis.mapper.TeacherMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by WCY on 2021/8/2
 */
public class MybatisTest {
    // 工厂一个
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void initSqlSessionFactory() {
        sqlSessionFactory = MybatisTestUtil.getSqlSessionFactory();
    }

    @Test
    public void mybatisProcess() {
        InputStream inputStream = this.getClass().getResourceAsStream("/mybatis-config.xml");
        // 1 根据全局配置文件创建一个SqlSessionFactory
        // SqlSessionFactory: 是SqlSession工厂, 负责创建SqlSession
        // SqlSession: sql会话(代表和数据库的一次会话)
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 2 获取和数据库的一次会话: getConnection()
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3 使用SqlSession操作数据库
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.selectEmpById(1);

        //Employee employee = sqlSession.selectOne("cn.alex.mybatis.mapper.EmployeeMapper.selectEmpById", 1);

        System.out.println("employee = " + employee);
        sqlSession.close();
    }

    @Test
    public void insertEmployee() {
        // 1 获取一次数据库会话 sqlSessionFactory.openSession(true) true 设置自动提交事务
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 2 获取接口的映射器
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            // 3 测试
            Employee employee = new Employee(null, "tomcat", "tomcat@qq.com", 0);
            int result = mapper.insertEmployee(employee);
            System.out.println("result = " + result);
            System.out.println("employee = " + employee);
            // 4 提交事务
            sqlSession.commit();
        }
        /*
            注意: 关闭资源一定要在finally里
            这里使用了jdk1.7新特性, 实现了Closeable接口, 定义在try()中的资源最后会调用资源的close()
         */
    }

    @Test
    public void insertSelectKey() {
        // 1 获取一次数据库会话 sqlSessionFactory.openSession(true) true 设置自动提交事务
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 2 获取接口的映射器
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            // 3 测试
            Employee employee = new Employee(null, "tomcat", "tomcat@qq.com", 0);
            int result = mapper.insertSelectKey(employee);
            System.out.println("result = " + result);
            System.out.println("employee = " + employee);
            // 4 提交事务
            sqlSession.commit();
        }
    }

    @Test
    public void selectById() {
        // 1 获取一次数据库会话 sqlSessionFactory.openSession(true) true 设置自动提交事务
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 2 获取接口的映射器
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            // 3 测试
            Employee employee = mapper.selectById(1);
            System.out.println("employee = " + employee);
            // 4 提交事务
            sqlSession.commit();
        }
    }

    @Test
    public void selectAll() {
        // 1 获取一次数据库会话 sqlSessionFactory.openSession(true) true 设置自动提交事务
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 2 获取接口的映射器
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            // 3 测试
            List<Employee> employeeList = mapper.selectAll();
            System.out.println("employeeList = " + employeeList);
            // 4 提交事务
            sqlSession.commit();
        }
    }

    @Test
    public void selectByIdReturnMap() {
        // 1 获取一次数据库会话 sqlSessionFactory.openSession(true) true 设置自动提交事务
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 2 获取接口的映射器
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            // 3 测试
            Map<String, Object> employeeMap = mapper.selectByIdReturnMap(1);
            System.out.println("employeeMap = " + employeeMap);
            // 4 提交事务
            sqlSession.commit();
        }
    }

    @Test
    public void selectAllReturnMap() {
        // 1 获取一次数据库会话 sqlSessionFactory.openSession(true) true 设置自动提交事务
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 2 获取接口的映射器
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            // 3 测试
            List<Map<String, Object>> employeeListMap = mapper.selectAllReturnMap();
            System.out.println("employeeListMap = " + employeeListMap);
            // 4 提交事务
            sqlSession.commit();
        }
    }

    /*
        默认mybatis自动封装结果集
        1 按照列名和属性名一一对应(不区分大小写)
        2 如果不一一对应
            开启驼峰命名(满足驼峰命名规则)
            起别名
            自定义结果集
     */
    @Test
    public void getKey() {
        Integer id = 1;
        // 1 获取一次数据库会话 sqlSessionFactory.openSession(true) true 设置自动提交事务
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 2 获取接口的映射器
            KeyMapper mapper = sqlSession.getMapper(KeyMapper.class);
            // 3 测试
            Key key = mapper.getById(id);
            System.out.println("key = " + key);

            Key keyLock = mapper.getKeyById(id);
            System.out.println("keyLock = " + keyLock);

            Key keyLockStep = mapper.getKeyByIdByStep(id);
            System.out.println("keyLockStep = " + keyLockStep);

            // 4 提交事务
            sqlSession.commit();
        }
    }

    @Test
    public void getLock() {
        Integer id = 3;
        // 1 获取一次数据库会话 sqlSessionFactory.openSession(true) true 设置自动提交事务
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 2 获取接口的映射器
            LockMapper mapper = sqlSession.getMapper(LockMapper.class);
            // 3 测试
            Lock lock = mapper.getById(id);
            System.out.println("lock = " + lock);

            Lock lockKey = mapper.getLockById(id);
            System.out.println("lockKey = " + lockKey);

            Lock lockKeyStep = mapper.getLockByIdByStep(id);
            System.out.println("lockKeyStep = " + lockKeyStep);

            List<Lock> lockList = mapper.getLock();

            // 4 提交事务
            sqlSession.commit();
        }
    }

    @Test
    public void getTeacher() {
        Integer id = 1;
        // 1 获取一次数据库会话 sqlSessionFactory.openSession(true) true 设置自动提交事务
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 2 获取接口的映射器
            TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
            // 3 测试
            Teacher teacher = mapper.getById(id);
            System.out.println("teacher = " + teacher);

            // id > name like birth <
            teacher.setId(0);
            teacher.setName("王");
            teacher.setBirth(new Date());
            List<Teacher> teacherList = mapper.getTeacherByCondition(teacher);
            System.out.println("teacherList = " + teacherList);

            List<Teacher> teacherList2 = mapper.getTeacherByCondition2(teacher);
            System.out.println("teacherList2 = " + teacherList2);

            List<Integer> idList = Arrays.asList(1, 2, 3);
            List<Teacher> teacherList3 = mapper.getTeacherByIdIn(idList);
            System.out.println("teacherList3 = " + teacherList3);

            // id = name = birth <
            teacher.setId(1);
            teacher.setName("王德发");
            teacher.setBirth(new Date());
            List<Teacher> teacherList4 = mapper.getTeacherByChoose(teacher);
            System.out.println("teacherList4 = " + teacherList4);

            // 4 提交事务
            sqlSession.commit();
        }
    }

    @Test
    public void updateTeacherBySelective() {
        // 1 获取一次数据库会话 sqlSessionFactory.openSession(true) true 设置自动提交事务
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 2 获取接口的映射器
            TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
            // 3 测试
            Teacher teacher = new Teacher();
            teacher.setId(1);
            teacher.setName("王有德");
            teacher.setCourse("数学");
            teacher.setAddress("马鞍山");
            teacher.setBirth(new Date());
            // name course address birth
            int result = mapper.updateBySelective(teacher);
            System.out.println("result = " + result);
            // 4 提交事务
            sqlSession.commit();
        }
    }

}

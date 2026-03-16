import cn.alex.mybatis.domain.Teacher;
import cn.alex.mybatis.mapper.TeacherMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by WCY on 2021/8/3
 */
public class MybatisCacheTest {
    private SqlSessionFactory sqlSessionFactory;

    /*
        获取sqlSessionFactory
        不能用SqlSession原因, 多线程下多个线程共用一个数据库连接
        要操作数据就拿个新的SqlSession
     */

    @Before
    public void initSqlSessionFactory() {
        // 初始化SqlSessionFactory
        sqlSessionFactory = MybatisTestUtil.getSqlSessionFactory();
    }

    /*
        Mybatis缓存机制: Map 能保存查询出的一些数据
        一级缓存: 线程级别的缓存, 本地缓存 SqlSession级别缓存
        二级缓存: 全局范围的缓存, 除了当前线程 SqlSession能用外, 其它也可以使用 namespace级别缓存

        一级缓存: Mybatis: SqlSession级别缓存, 默认存在
        机制: 只要之前查询过的数据, Mybatis就会保存在缓存中(Map), 下次获取直接从缓存中拿

        二级缓存: 一级缓存SqlSession关闭或提交以后, 一级缓冲中的数据会放在二级缓存中
                Mybatis默认没有使用的 配置
        1 全局配置开启二级缓存 <setting name="cacheEnabled" value="true"/>
        2 配置某个Mapper.xml文件, 让其开启二级缓存 <cache/>
          配置domain实现Serializable接口

        1 一级缓存和二级缓存中有同一个数据
            二级缓存中, 一级缓存关闭就有了数据
            一级缓存中, 二级缓存中没有数据, 就会看一级缓存, 一级缓存也没有数据,
            查询数据库, 把数据库结果放在一级缓存
        2 任何时候都是先看二级缓存, 再看一级缓存, 如果大家都没有就去查询数据库
          二 一 库(优衣库)
          每个Mapper有它自己的二级缓存
     */

    /*
        一级缓存失效的几种情况
        一级缓存是SqlSession级别的缓存
        1 不用的SqlSession使用不同的一级缓存
            只有在同一个SqlSession期间查询到的数据会保存在这个SqlSession的缓存中
            下次使用这个SqlSession会从缓存中拿
        2 同一个方法, 不同的参数, 由于可能之前没有查过, 所以还会查询
        3 只要在这个SqlSession期间执行过任何一次增删改操作, 增删改操作会把缓存清空
        4 手动清空缓存

        每次查询, 先看一级缓存中有没有, 如果没有就去发送新的sql查询, 每个SqlSession拥有自己的一级缓存
     */

    // 观察二级缓存是否失效根据控制台是否二次打印查询语句, 二级缓存是将数据序列化存储, 每次反序列化的数据不一致
    @Test
    public void level1Cache() {
        // 1 获取一次数据库会话 sqlSessionFactory.openSession(true) true 设置自动提交事务
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            // 2 获取接口的映射器
            TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
            // 3 测试
            Teacher teacher = mapper.getById(1);
            System.out.println("teacher = " + teacher);
            //sqlSession.clearCache(); // 清空一级缓存
            System.out.println("----------------");
            Teacher teacher2 = mapper.getById(1);
            System.out.println("teacher2 = " + teacher2);
            System.out.println(teacher == teacher2); // true
            // 4 提交事务 查询不需要提交事务
        }

        // 1 获取一次数据库会话 sqlSessionFactory.openSession(true) true 设置自动提交事务
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true);
             SqlSession sqlSession2 = sqlSessionFactory.openSession(true)) {
            // 第一个会话
            TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);

            Teacher teacher = mapper.getById(1);
            System.out.println("teacher = " + teacher);

            // 第二个会话
            TeacherMapper mapper2 = sqlSession2.getMapper(TeacherMapper.class);
            Teacher teacher2 = mapper2.getById(1);
            System.out.println("teacher2 = " + teacher2);
            System.out.println(teacher == teacher2); // 反序列化对象 false
            // 4 提交事务 查询不需要提交事务
        }
    }

    @Test
    public void level2Cache() {
        // 1 获取一次数据库会话 sqlSessionFactory.openSession(true) true 设置自动提交事务
        try {
            // 第一个会话
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);

            Teacher teacher = mapper.getById(1);
            System.out.println("teacher = " + teacher);

            sqlSession.close();

            // 第二个会话
            SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
            TeacherMapper mapper2 = sqlSession2.getMapper(TeacherMapper.class);
            Teacher teacher2 = mapper2.getById(1);
            System.out.println("teacher2 = " + teacher2);
            sqlSession2.close();
            // 4 提交事务 查询不需要提交事务
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
        }
    }

}

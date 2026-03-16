import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by WCY on 2021/8/2
 */
public class MybatisTestUtil {

    public static SqlSessionFactory getSqlSessionFactory() {
        return getSqlSessionFactory("mybatis-config.xml");
    }

    public static SqlSessionFactory getSqlSessionFactory(String configFile) {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

}

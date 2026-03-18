package cn.alex.mybatis.plus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by WCY on 2021/4/5
 */
@Slf4j
@Component // 一定不要忘记把处理器加入IOC容器中
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final String CREATE_USER = "createUser";
    private static final String CREATE_TIME = "createTime";
    private static final String UPDATE_USER = "updateUser";
    private static final String UPDATE_TIME = "updateTime";

    /**
     * 插入时的填充策略
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill...");
        Date currentDate = new Date();
        this.setFieldValByName(CREATE_TIME, currentDate, metaObject);
        this.setFieldValByName(UPDATE_TIME, currentDate, metaObject);
    }

    /**
     * 更新时的填充策略
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill...");
        this.setFieldValByName(UPDATE_TIME, new Date(), metaObject);
    }

}

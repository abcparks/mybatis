package cn.alex.multiple.datasource.service;

import cn.alex.multiple.datasource.constant.TransactionManagerConstant;
import cn.alex.multiple.datasource.datasource.annotation.MultiTransactional;
import cn.alex.multiple.datasource.domain.Teacher;
import cn.alex.multiple.datasource.mapper.primary.TeacherMapper;
import cn.alex.multiple.datasource.mapper.secondary.TTeacherMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by WCY on 2022/9/2
 */
@Slf4j
@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private TTeacherMapper tTeacherMapper;

    @Autowired
    private DataSourceTransactionManager primaryTransactionManager;

    @Autowired
    private DataSourceTransactionManager secondaryTransactionManager;

    @Autowired
    private TransactionTemplate primaryTransactionTemplate;

    @Autowired
    private TransactionTemplate secondaryTransactionTemplate;


    // mysql, oracle批量插入并返回主键
    public void insertTeacher(Teacher teacher) throws Exception {
        List<Teacher> teacherList = Arrays.asList(teacher.clone(), teacher.clone(), teacher.clone());

        int result = teacherMapper.insertTeacher(teacher);
        log.info("primary: result: {}, id: {}", result, teacher.getId());
        result = teacherMapper.batchInsertTeacher(teacherList);

        result = tTeacherMapper.insertTeacher(teacher);
        log.info("secondary: result: {}, id: {}", result, teacher.getId());
        result = tTeacherMapper.batchInsertTeacher(teacherList);

        teacherList.forEach(t -> t.setId(tTeacherMapper.getMybatisSequence()));
        result = tTeacherMapper.batchInsertTeacherPrepared(teacherList);

        System.out.println("Hello World!");
    }

    // 编程性事务: 业务和事务控制耦合
    public void programmaticTransactionByManager(Teacher teacher) {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        // getTransaction(transactionDefinition) -> doBegin(transaction, def) -> con.setAutoCommit(false) 会关闭自动提交
        TransactionStatus primaryStatus = primaryTransactionManager.getTransaction(transactionDefinition);
        TransactionStatus secondaryStatus = secondaryTransactionManager.getTransaction(transactionDefinition);

        // primaryTransactionManager -> primaryDataSource 控制 TeacherMapper
        // secondaryTransactionManager -> secondaryDataSource 控制 TTeacherMapper

        try {
            // 所有的业务
            teacherMapper.insertTeacher(teacher);
            tTeacherMapper.insertTeacher(teacher);
            int result = 1 / 0;
            // 多个事务, 先提交后面的事务
            secondaryTransactionManager.commit(secondaryStatus);
            primaryTransactionManager.commit(primaryStatus);
        } catch (Exception e) {
            // 多个事务, 先回滚后面的事务
            secondaryTransactionManager.rollback(secondaryStatus);
            primaryTransactionManager.rollback(primaryStatus);
        }
    }

    public void programmaticTransactionByTemplate(Teacher teacher) {
        primaryTransactionTemplate.execute(primaryStatus ->
                secondaryTransactionTemplate.execute(secondaryStatus -> {
                    try {
                        // 所有的业务
                        teacherMapper.insertTeacher(teacher);
                        tTeacherMapper.insertTeacher(teacher);
                        int result = 1 / 0;
                    } catch (Exception e) {
                        e.printStackTrace();
                        primaryStatus.setRollbackOnly();
                        secondaryStatus.setRollbackOnly();
                        return false;
                    }
                    return true;
                })
        );
    }

    // 申明式事务
    // @Transactional 若存在多个事务管理器, 需指定事务管理器, 只能回滚该事务管理器管理的事务
    //@Transactional(transactionManager = "primaryTransactionManager")
    @MultiTransactional(value = {TransactionManagerConstant.PRIMARY_TRANSACTION_MANAGER, TransactionManagerConstant.SECONDARY_TRANSACTION_MANAGER})
    public void declarativeTransaction(Teacher teacher) {
        // 所有的业务
        teacherMapper.insertTeacher(teacher);
        tTeacherMapper.insertTeacher(teacher);
        //int result = 1 / 0;
    }

    // 需要获取Spring代理对象
    @Autowired
    private TeacherService teacherService;

    @Transactional(transactionManager = "primaryTransactionManager")
    public void declarativeTransactionByStep(Teacher teacher) {
        teacherMapper.insertTeacher(teacher);
        //declarativeTransactionByStep2(teacher); // declarativeTransactionByStep2不会回滚
        teacherService.declarativeTransactionByStep2(teacher);
    }

    @Transactional(transactionManager = "secondaryTransactionManager")
    public void declarativeTransactionByStep2(Teacher teacher) {
        tTeacherMapper.insertTeacher(teacher);
        int result = 1 / 0;
    }

}

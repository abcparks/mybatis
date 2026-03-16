package cn.alex.multiple.datasource.datasource.aop;

import cn.alex.multiple.datasource.datasource.annotation.MultiTransactional;
import javafx.util.Pair;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Stack;

/**
 * Created by WCY on 2022/9/4
 */
@Aspect
@Component
public class MultiTransactionalAspect {

    @Autowired
    private ApplicationContext applicationContext;

    // 默认事务信息
    private final DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();

    // 每个线程一个栈
    private static final ThreadLocal<Stack<Pair<PlatformTransactionManager, TransactionStatus>>> THREAD_LOCAL = new ThreadLocal<>();

    @Pointcut("@annotation(cn.alex.multiple.datasource.datasource.annotation.MultiTransactional)")
    public void pointcut() {

    }

    @Before("pointcut() && @annotation(multiTransactional)")
    public void multiTransactional(MultiTransactional multiTransactional) {
        Stack<Pair<PlatformTransactionManager, TransactionStatus>> pairStack = new Stack<>();
        String[] transactionManagerNames = multiTransactional.value();
        for (String transactionManagerName : transactionManagerNames) {
            PlatformTransactionManager transactionManager = applicationContext.getBean(transactionManagerName, DataSourceTransactionManager.class);
            TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
            pairStack.push(new Pair<>(transactionManager, transactionStatus));
        }
        THREAD_LOCAL.set(pairStack);
    }

    @AfterReturning("pointcut()")
    public void afterReturning() {
        // 栈顶弹出(后进先出)
        Stack<Pair<PlatformTransactionManager, TransactionStatus>> pairStack = THREAD_LOCAL.get();
        while (!pairStack.empty()) {
            Pair<PlatformTransactionManager, TransactionStatus> pair = pairStack.pop();
            pair.getKey().commit(pair.getValue());
        }
        THREAD_LOCAL.remove();
    }

    @AfterThrowing(value = "pointcut()")
    public void afterThrowing() {
        // 栈顶弹出(后进先出)
        Stack<Pair<PlatformTransactionManager, TransactionStatus>> pairStack = THREAD_LOCAL.get();
        while (!pairStack.empty()) {
            Pair<PlatformTransactionManager, TransactionStatus> pair = pairStack.pop();
            pair.getKey().rollback(pair.getValue());
        }
        THREAD_LOCAL.remove();
    }
}
